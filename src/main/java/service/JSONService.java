package service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.jsoup.Jsoup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Сервис для сериализации блюд.
 */
public class JSONService {
    private static final Gson gson = new Gson();
    private static FileWriter fileWriter;

    /**
     * Сохранить блюда в json.
     */
    public static void saveDishes(List<Dish> dishes, String path) {
        try {
            Type dishesType = new TypeToken<List<Dish>>() {
            }.getType();

            fileWriter = new FileWriter(path);
            String jsonString = gson.toJson(dishes, dishesType);
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Загрузить блюда из json.
     */
    public static List<Dish> loadDishes(String path) {
        Type listOfDishObject = new TypeToken<ArrayList<Dish>>() {
        }.getType();

        List<Dish> dishes = null;
        try {
            dishes = gson.fromJson(new FileReader(path), listOfDishObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dishes;
    }

    /**
     * Ищет блюдо в отсортированном json
     * @param json отсортированный json по увеличению недостоющих ингредиентов
     * @return возвращает найденное блюдо
     */
    public static List<Dish> GetDishes(String json, int dishesAmount) {
        List<Dish> dishes = new ArrayList<>();
        Recipe recipe;
        @Deprecated
        JsonElement root = new JsonParser().parse(json);
        JsonObject object = root.getAsJsonObject();
        JsonArray results = object.get("results").getAsJsonArray();
        if (results.size() > 0) {
            for (int i = 0; i < dishesAmount; i++) {
                JsonObject dishInformation = results.get(i).getAsJsonObject();
                String dishTitle = dishInformation.get("title").toString();
                dishTitle = dishTitle.substring(1, dishTitle.length() - 1); // избавляемся от кавычек
                if (dishInformation.get("usedIngredientCount").getAsInt() != 0) { // заходим сюда, если блюдо ищем по ингредиентоам
                    if (dishInformation.get("missedIngredients").getAsJsonArray().size() == 0) {
                        recipe = getRecipe(dishInformation);
                    } else { // выходим из метода, т.к. json отсортирован, т.е. дальше не будет подходящих блюд
                        break;
                    }
                } else { // заходим сюда, если ищем блюдо по названию
                    recipe = getRecipe(dishInformation);
                }
                Dish dish = new Dish(dishTitle, recipe, dishInformation.get("image").getAsString(),
                        Jsoup.parse(dishInformation.get("summary").getAsString()).text());
                dishes.add(dish);
            }
        }
        if (dishes.size() == 0)
            return null;
        return dishes;
    }

    /**
     * Возвращает рецепт по информации о блюде
     */
    private static Recipe getRecipe(JsonObject dishInformation) {
        JsonArray phasesInformation = dishInformation.get("analyzedInstructions").getAsJsonArray();
        List<CookPhase> phases = getPhases(phasesInformation);
        List<Product> products = getProducts(dishInformation);
        return new Recipe(products, phases);
    }

    /**
     * Возвращает этапы готовки по информации о этапах готовки
     */
    private static List<CookPhase> getPhases(JsonArray phasesInformation) {
        List<CookPhase> phases = new ArrayList<>();
        for (int i = 0; i < phasesInformation.size(); i++) {
            JsonObject currentPhaseInformation = phasesInformation.get(i).getAsJsonObject();
            String summery = currentPhaseInformation.get("name").getAsString();
            JsonArray stepsInformation = currentPhaseInformation.get("steps").getAsJsonArray();
            List<CookPhaseStep> steps = getSteps(stepsInformation);
            phases.add(new CookPhase(summery, steps));
        }
        return phases;
    }

    /**
     * Возвращает шаги готовки по информации о шагах готовки
     */
    private static List<CookPhaseStep> getSteps(JsonArray stepsInformation) {
        List<CookPhaseStep> steps = new ArrayList<>();
        for (int j = 0; j < stepsInformation.size(); j++) {
            HashSet<Ingredient> ingredients = new HashSet<>();
            JsonObject currentStepInformation = stepsInformation.get(j).getAsJsonObject();
            String description = currentStepInformation.get("step").getAsString();
            JsonArray ingredientsInformation = currentStepInformation.get("ingredients").getAsJsonArray();
            for (int k = 0; k < ingredientsInformation.size(); k++) {
                Ingredient ingredient = new Ingredient(
                        ingredientsInformation.get(k).getAsJsonObject().get("name").getAsString());
                ingredients.add(ingredient);
            }
            steps.add(new CookPhaseStep(description, ingredients));
        }
        return steps;
    }

    /**
     * Возвращает рецепт по информации о блюде
     */
    private static List<Product> getProducts(JsonObject dishInformation) {

        List<Product> products = new ArrayList<>();
        JsonArray productsInformation = dishInformation.get("extendedIngredients").getAsJsonArray();
        for (int i = 0; i < productsInformation.size(); i++) {
            JsonObject currentProductInformation = productsInformation.get(i).getAsJsonObject();
            Product product = new Product(
                    new Ingredient(currentProductInformation.get("name").getAsString()),
                    currentProductInformation.get("originalString").getAsString());
            products.add(product);
        }
        return products;
    }
}

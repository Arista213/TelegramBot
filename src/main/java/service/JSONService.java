package service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Dish;
import model.Product;
import model.Recipe;

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
     *
     * @param json отсортированный json по увеличению недостоющих ингредиентов
     * @return возвращает найденное блюдо
     */
    public static List<Dish> GetDishes(String json, int dishesAmount) {
        List<Dish> dishes = new ArrayList<>();
        List<Product> products;
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
                        products = getRecipe(dishInformation);
                    } else { // выходим из метода, т.к. json отсортирован, т.е. дальше не будет подходящих блюд
                        break;
                    }
                } else { // заходим сюда, если ищем блюдо по названию
                    products = getRecipe(dishInformation);
                }
                Dish dish = new Dish(dishTitle, new Recipe(products));
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
    private static List<Product> getRecipe(JsonObject dishInformation) {
        List<Product> recipe = new ArrayList<>();
        JsonArray productsInformation = dishInformation.get("extendedIngredients").getAsJsonArray();
        for (int i = 0; i < productsInformation.size(); i++) {
            JsonObject currentProductInformation = productsInformation.get(i).getAsJsonObject();
            Product product = new Product(currentProductInformation.get("name").getAsString());
            recipe.add(product);
        }
        return recipe;
    }
}

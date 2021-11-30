package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
     * Ищет блюдо в строке json
     * @param json json в виде строки
     * @return возвращает найденное блюдо
     */
    public static List<Dish> GetDishes(String json, int dishesCount) {
        List<Dish> dishes = new ArrayList<>();
        @Deprecated
        JsonElement root = new JsonParser().parse(json);
        var object = root.getAsJsonObject();
        try {
            for (int i = 0; i < dishesCount; i++) {
                var results = object.get("results").getAsJsonArray().get(i).getAsJsonObject();
                var dishTitle = results.get("title").toString();
                dishTitle = dishTitle.substring(1, dishTitle.length() - 1); // избавляемся от кавычек
                var steps = results.get("analyzedInstructions")
                        .getAsJsonArray().get(0).getAsJsonObject().get("steps").getAsJsonArray();
                Set<Product> products = new HashSet<>();
                getDishFromSteps(steps, products);
                Recipe recipe = new Recipe(new ArrayList<>(products));
                dishes.add(new Dish(dishTitle, recipe));
            }
            return dishes;
        }

        catch (NullPointerException | IndexOutOfBoundsException exception) {
            if (dishes.size() == 0)
                return null;
            return dishes;
        }
    }

    /**
     * Возвращает ингредиенты с каждого шага готовки
     */
    private static void getDishFromSteps(JsonArray steps, Set<Product> products) {
        for (JsonElement step : steps) {
            var ingredients = step.getAsJsonObject().get("ingredients").getAsJsonArray();
            for (JsonElement ingredient : ingredients) {
                var name = ingredient.getAsJsonObject().get("name").toString();
                name = name.substring(1, name.length() - 1); // избавляемся от кавычек
                products.add(new Product(name));
            }
        }
    }


}

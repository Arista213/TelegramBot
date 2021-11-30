package service;

import model.Dish;
import model.Product;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для взаимодействия с API
 */
public class APIService {
    /**
     * Метод ищет блюдо по его названию в API Spoonacular
     * @param title название искаемого блюда
     * @return найденное блюдо
     */
    public static Dish getDishByTitle(String title) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/complexSearch?apiKey=69c64921b39747cb935f82cf5c8de154&query=" + title + "&number=1&addRecipeInformation=true")
                .get()
                .build();
        try {
            String json = Objects.requireNonNull(client.newCall(request).execute().body()).string();
            List<Dish> dishes = JSONService.GetDishes(json, 1);
            if (dishes == null) {
                return null;
            }
            return dishes.get(0);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Метод ищет блюдо по его названию в API Spoonacular
     * @param ingredients ингредиенты блюда, которое ищется
     * @return найденное блюдо
     */
    public static List<Dish> getDishesByIngredients(List<Product> ingredients) {
        OkHttpClient client = new OkHttpClient();
        List<String> ingredientsNames = new ArrayList<>();
        for (Product ingredient: ingredients) {
            ingredientsNames.add(ingredient.toString());
        }
        String joinedIngredients = String.join(",", ingredientsNames);
        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/complexSearch?apiKey=69c64921b39747cb935f82cf5c8de154&includeIngredients=" + joinedIngredients + "&number=5&addRecipeInformation=true")
                .get()
                .build();
        try {
            String json = Objects.requireNonNull(client.newCall(request).execute().body()).string();
            return JSONService.GetDishes(json, 5);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

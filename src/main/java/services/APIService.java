package services;

import constants.Config;
import models.Dish;
import models.Ingredient;
import models.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для взаимодействия с API
 */
public class APIService {
    private final UserService userService;

    public APIService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод ищет блюдо по его названию в API Spoonacular
     *
     * @param title название искаемого блюда
     * @return найденное блюдо
     */
    public Dish getDishByTitle(String title, User user) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format
                        ("https://api.spoonacular.com/recipes/complexSearch?apiKey=%s&number=1&addRecipeInformation=true&fillIngredients=true&query=%s&diet=%s&intolerances=%s",
                                Config.API_KEY.toStringValue(), title, userService.getDiet(user), userService.getIntolerancesString(user)))
                .get()
                .build();
        try {
            String json = Objects.requireNonNull(client.newCall(request).execute().body()).string();
            List<Dish> dishes = JsonService.GetDishes(json, 1);
            if (dishes == null) {
                return null;
            }
            return dishes.get(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Метод ищет блюдо по его названию в API Spoonacular
     *
     * @param ingredients ингредиенты блюда, которое ищется
     * @return найденное блюдо
     */
    public List<Dish> getDishesByIngredients(Collection<Ingredient> ingredients, User user) {
        OkHttpClient client = new OkHttpClient();
        List<String> ingredientsTitles = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ingredientsTitles.add(ingredient.getTitle());
        }
        String joinedIngredients = String.join(",", ingredientsTitles);
        Request request = new Request.Builder()
                .url(String.format(
                        "https://api.spoonacular.com/recipes/complexSearch?apiKey=%s&sort=min-missing-ingredients&fillIngredients=true&addRecipeInformation=true&includeIngredients=%s&diet=%s&intolerances=%s"
                        , Config.API_KEY.toStringValue(), joinedIngredients, userService.getDiet(user), userService.getIntolerancesString(user)))
                .get()
                .build();
        try {
            String json = Objects.requireNonNull(client.newCall(request).execute().body()).string();
            return JsonService.GetDishes(json, 5);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

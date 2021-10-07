import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.Dishes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishesTests {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        Dishes.dishesList = new ArrayList<>();
        Dishes.dishesList.add(new Dish("Яишница", Arrays.asList("яйца")));
        Dishes.dishesList.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }


    @Test
    void recipeByNameTest() {
        String dishName = "Блины";
        Dish pancakes = new Dish("Блины", Arrays.asList("мука", "яйца", "молоко"));
        Dish dish = Dishes.getDishByName(dishName);     
    }

    @Test
    void botRecipeByIngredientsTest() {
        bot.receive("/recipe_ingredients");
        String answer = bot.waitForOutput();
        String excepted = "Введите ингредиенты, которые у вас имеются";
        assertEquals(excepted, answer);

        bot.receive("яйца молоко мука");
        answer = bot.waitForOutput();
        excepted = "Можно приготовить: Яишница\n" +
                "------Рецепт------\t\n" +
                "яйца\n" +
                "------------------\n" +
                "\n" +
                "Можно приготовить: Блины\n" +
                "------Рецепт------\t\n" +
                "яйца\n" +
                "мука\n" +
                "молоко\n" +
                "------------------\n" +
                "\n";
        assertEquals(excepted, answer);
    }
}

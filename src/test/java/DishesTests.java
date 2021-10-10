import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Модульные тесты на класс Dishes - ответственный за все действия с блюдами
 */
public class DishesTests {
    Bot bot;
    List<Dish> dishesList;

    /**
     * В тестах список блюд изменяется, поэтому для каждого теста он становится прежним.
     */
    @BeforeEach
    void setUp() {
        bot = new Bot();
        dishesList = new ArrayList<>();
        dishesList.add(new Dish("Яишница", Arrays.asList("яйца")));
        dishesList.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
        DishService.dishes = dishesList;
    }

    @Test
    void recipeByNameTest() {
        String dishName = "Блины";
        Dish pancakes = new Dish("Блины", Arrays.asList("яйца", "мука", "молоко"));
        Dish dish = DishService.getDishByName(dishName);
        assertEquals(pancakes.name, dish.name);
        assertEquals(pancakes.getRecipe(), dish.getRecipe());
    }

    @Test
    void recipeByNameButNameDidNotFindTest() {
        String dishName = "Тест";
        Dish dish = DishService.getDishByName(dishName);
        assertEquals("", dish.name);
        assertEquals("", dish.getRecipe());
    }

    @Test
    void recipeByIngredientsButIngredientsIsNotFitTest() {
        HashSet<String> ingredients = new HashSet<>();
        ingredients.add("Тест");
        String result = DishService.whatCanBeCooked(ingredients);
        assertEquals("", result);
    }

    @Test
    void recipeByIngredientsTest() {
        HashSet<String> ingredients = new HashSet<>();
        ingredients.add("мука");
        ingredients.add("яйца");
        ingredients.add("молоко");
        String result = DishService.whatCanBeCooked(ingredients);
        String expected =
                "Можно приготовить: Яишница\n" +
                "яйца\n" +
                "Можно приготовить: Блины\n" +
                "яйца мука молоко\n";
        assertEquals(expected, result);
    }
}

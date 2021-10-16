import logic.Bot;
import logic.User;
import logic.cook.Dish;
import logic.cook.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Модульные тесты на класс Dishes - ответственный за все действия с блюдами
 */
public class DishesTests {
    Bot bot;
    TestCommunicateService communicateService;
    List<Dish> dishesList;

    /**
     * В тестах список блюд изменяется, поэтому для каждого теста он становится прежним.
     */
    @BeforeEach
    void setUp() {
        bot = new Bot(communicateService, new User());
        dishesList = new ArrayList<>();
        dishesList.add(new Dish("Яишница", List.of("яйца")));
        dishesList.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
        DishService.dishes = dishesList;
    }

    @Test
    void recipeByNameTest() {
        String dishName = "Блины";
        Dish pancakes = new Dish("Блины", Arrays.asList("яйца", "мука", "молоко"));
        Dish dish = DishService.getDishByTitle(dishName);

        assertEquals(pancakes.title, dish.title);
        assertTrue(pancakes.getRecipe().isEqual(dish.getRecipe()));
    }

    @Test
    void recipeByNameButNameDidNotFindTest() {
        String dishName = "Тест";
        Dish dish = DishService.getDishByTitle(dishName);
        assertFalse(dish.isExist);
    }

    @Test
    void recipeByIngredientsButIngredientsIsNotFitTest() {
        HashSet<String> ingredients = new HashSet<>();
        ingredients.add("Тест");
        List<Dish> result = DishService.getValidDishes(ingredients);
        assertEquals(new ArrayList<Dish>(), result);
    }

    @Test
    void recipeByIngredientsTest() {
        HashSet<String> ingredients = new HashSet<>();
        ingredients.add("мука");
        ingredients.add("яйца");
        ingredients.add("молоко");
        List<Dish> dishes = DishService.getValidDishes(ingredients);
        String expected = "Яишница\n" +
                "яйца\n" +
                "\n" +
                "Блины\n" +
                "яйца мука молоко\n" +
                "\n";
        assertEquals(expected, DishService.getStringFromDishes(dishes));
    }
}

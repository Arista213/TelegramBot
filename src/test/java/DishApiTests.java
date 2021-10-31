import api.DishApi;
import model.Dish;
import model.Product;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульные тесты на класс Dishes - ответственный за все действия с блюдами.
 */
public class DishApiTests {
    private final Dish pancakes = new Dish("Блины", new Recipe(Arrays.asList(
            new Product("яйца"),
            new Product("мука"),
            new Product("молоко")
    )));

    private final Dish friedEggs = new Dish("Яичница", new Recipe(List.of(new Product("яйца"))));

    /**
     * В тестах список блюд изменяется, поэтому для каждого теста он становится прежним.
     */
    @BeforeEach
    void setUp() {
        DishApi.initiate();
    }

    @Test
    void recipeByNameTest() {
        String dishName = "Блины";
        Dish dish = DishApi.findDishByTitle(dishName);

        assertEquals(pancakes, dish);
    }

    @Test
    void recipeByNameButNameDidNotFindTest() {
        String dishName = "Тест";
        Dish dish = DishApi.findDishByTitle(dishName);
        assertFalse(dish.isExist);
    }

    @Test
    void recipeByproductsButProductsIsNotFitTest() {
        Set<Product> products = new HashSet<>();
        products.add(new Product("Тест"));
        Set<Dish> result = DishApi.getAvailableForUser(products);
        assertEquals(new HashSet<Dish>(), result);
    }

    @Test
    void recipeByproductsTest() {
        Set<Product> products = new HashSet<>();
        products.add(new Product("мука"));
        products.add(new Product("яйца"));
        products.add(new Product("молоко"));

        Set<Dish> validDishes = DishApi.getAvailableForUser(products);
        Set<Dish> expectedDishes = new HashSet<>();
        expectedDishes.add(pancakes);
        expectedDishes.add(friedEggs);

        assertTrue(expectedDishes.containsAll(validDishes));
    }
}

import api.DishApi;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для класса DishApi, ответственного за работу с блюдами.
 */
public class DishApiTests {
    private final Dish pancakes = new Dish("Блины", new Recipe(Arrays.asList(
            new Product(new Ingredient("яйца"), "3 яйца"),
            new Product(new Ingredient("мука"), "1 стакан муки"),
            new Product(new Ingredient("молоко"), "2 стакана молока")),
            null), null, null);

    private final Dish friedEggs = new Dish("Яичница", new Recipe(List.of(
            new Product(new Ingredient("яйца"), "2 яица")), null),
            null, null);

    /**
     * В тестах список блюд изменяется, поэтому для каждого теста он становится прежним.
     */
    @BeforeEach
    void setUp() {
        DishApi.initiate();
    }

    /**
     * Поиск по названию блюда должен вернуть блюдо.
     */
    @Test
    void findDishByTitleTest() {
        String dishName = "Блины";
        Dish dish = DishApi.findDishByTitle(dishName);

        assertEquals(pancakes, dish);
    }

    /**
     * Поиск блюда по названию должен вернуть null.
     */
    @Test
    void findDishByTitleButNameDidNotFindTest() {
        String dishName = "Тест";
        Dish dish = DishApi.findDishByTitle(dishName);
        assertNull(dish);
    }

    /**
     * Поиск блюда по продуктам должен вернуть пустой лист.
     */
    @Test
    void findDishesByProductsButProductsIsNotFitTest() {
        List<Ingredient> products = new ArrayList<>();
        products.add(new Ingredient("Тест"));
        List<Dish> result = DishApi.findDishesByIngredients(products);
        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Поиск по заданным продуктам должен вернуть 2 блюда.
     */
    @Test
    void findDishesByProductsTest() {
        List<Ingredient> products = new ArrayList<>();
        products.add(new Ingredient("мука"));
        products.add(new Ingredient("яйца"));
        products.add(new Ingredient("молоко"));

        Set<Dish> validDishes = new HashSet<>(DishApi.findDishesByIngredients(products));
        Set<Dish> expectedDishes = new HashSet<>();
        expectedDishes.add(pancakes);
        expectedDishes.add(friedEggs);

        assertEquals(expectedDishes, validDishes);
    }
}

import api.DishApi;
import model.Dish;
import model.Product;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для класса DishApi, ответственного за работу с блюдами.
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
        List<Product> products = new ArrayList<>();
        products.add(new Product("Тест"));
        List<Dish> result = DishApi.findDishesByProducts(products);
        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Поиск по заданным продуктам должен вернуть 2 блюда.
     */
    @Test
    void findDishesByProductsTest() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("мука"));
        products.add(new Product("яйца"));
        products.add(new Product("молоко"));

        List<Dish> validDishes = DishApi.findDishesByProducts(products);
        List<Dish> expectedDishes = new ArrayList<>();
        expectedDishes.add(pancakes);
        expectedDishes.add(friedEggs);

        assertEquals(validDishes, expectedDishes);
    }
}

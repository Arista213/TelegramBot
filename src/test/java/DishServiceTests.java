import dao.impl.SimpleDishDao;
import model.Dish;
import model.Product;
import model.Recipe;
import org.junit.jupiter.api.Test;
import service.DishService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для класса DishApi, ответственного за работу с блюдами.
 */
public class DishServiceTests {
    private final Dish pancakes = new Dish("Блины", new Recipe(Arrays.asList(
            new Product("яйца"),
            new Product("мука"),
            new Product("молоко")
    )));

    private final Dish friedEggs = new Dish("Яичница", new Recipe(List.of(new Product("яйца"))));

    /**
     * Поиск по названию блюда должен вернуть блюдо.
     */
    @Test
    void findDishByTitleTest() {
        DishService dishService = new DishService(new SimpleDishDao());
        String dishName = "Блины";
        Dish dish = dishService.findDishByTitle(dishName);
        assertEquals(pancakes, dish);
    }

    /**
     * Поиск блюда по названию должен вернуть null.
     */
    @Test
    void findDishByTitleButNameDidNotFindTest() {
        DishService dishService = new DishService(new SimpleDishDao());
        String dishName = "Тест";
        Dish dish = dishService.findDishByTitle(dishName);
        assertNull(dish);
    }

    /**
     * Поиск блюда по продуктам должен вернуть пустой лист.
     */
    @Test
    void findDishesByProductsButProductsIsNotFitTest() {
        DishService dishService = new DishService(new SimpleDishDao());
        List<Product> products = new ArrayList<>();
        products.add(new Product("Тест"));
        List<Dish> result = dishService.findDishesByProducts(products);
        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Поиск по заданным продуктам должен вернуть 2 блюда.
     */
    @Test
    void findDishesByProductsTest() {
        DishService dishService = new DishService(new SimpleDishDao());
        List<Product> products = new ArrayList<>();
        products.add(new Product("мука"));
        products.add(new Product("яйца"));
        products.add(new Product("молоко"));

        Set<Dish> validDishes = new HashSet<>(dishService.findDishesByProducts(products));
        Set<Dish> expectedDishes = new HashSet<>();
        expectedDishes.add(pancakes);
        expectedDishes.add(friedEggs);

        assertEquals(validDishes, expectedDishes);
    }
}

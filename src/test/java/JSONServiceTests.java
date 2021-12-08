import model.Dish;
import model.Product;
import model.Recipe;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import service.JSONService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты на сервис для работы с json.
 */
public class JSONServiceTests {
    /**
     * Тест проверяет JSONService на сохрание блюд и их загрузку.
     */
    @Test
    public void jsonServiceSaveAndLoadTest() {
        String path = "target" + System.getProperty("file.separator") + "JsonSerialisationTest.txt";
        Dish friedEggs = new Dish("Яишница", new Recipe(List.of(new Product("яица"))));
        Dish pancakes = new Dish("Блины", new Recipe(Arrays.asList(
                new Product("яйца"),
                new Product("тесто"),
                new Product("молоко")
        )));

        List<Dish> expectedDishes = Arrays.asList(pancakes, friedEggs);
        JSONService.saveDishes(expectedDishes, path);
        List<Dish> actualDishes = JSONService.loadDishes(path);

        assertEquals(expectedDishes, actualDishes);
    }

    /**
     * Тест проверяет JSONService на поиск определенного количества блюд из JSON.
     */
    @Test
    public void getDishesTest() throws IOException {
        String path = "target" + System.getProperty("file.separator") + "JsonGetDishesTest.txt";
        Dish dish1 = new Dish("African Chicken Peanut Stew", new Recipe(List.of(
                new Product("bell peppers"), new Product("chicken"), new Product("cooking oil"),
                new Product("curry paste"), new Product("garlic cloves"), new Product("ginger"),
                new Product("groundnut"), new Product("onions"), new Product("bell pepper"),
                new Product("salt"), new Product("seasoning"), new Product("sweet potato"),
                new Product("thyme"), new Product("tomato"), new Product("tomato")
                )));
        Dish dish2 = new Dish("Roasted red peppers and tomatoes salad", new Recipe(List.of(
                new Product("red bell pepper"),  new Product("tomato"),  new Product("red onion"),
                new Product("capers"),  new Product("olive oil"),  new Product("coarse salt"),
                new Product("pepper"),  new Product("fresh thyme")
        )));
        List<Dish> expectedDishes = List.of(dish1, dish2);
        File file = new File(path);
        String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        List<Dish> actualDishes = JSONService.GetDishes(json, 2);
        assertEquals(expectedDishes, actualDishes);
    }
}

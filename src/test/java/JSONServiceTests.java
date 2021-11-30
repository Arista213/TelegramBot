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
                new Product("sweet potato"), new Product("seasoning"), new Product("salt"),
                new Product("garlic"), new Product("pepper"), new Product("salt and pepper"),
                new Product("peanut butter"), new Product("onion"), new Product("tomato"),
                new Product("sauce"), new Product("water"), new Product("thyme"),
                new Product("parsley"), new Product("bell pepper"), new Product("potato"),
                new Product("ginger"), new Product("cooking oil"), new Product("whole chicken"),
                new Product("stock"), new Product("curry powder"), new Product("white rice")
                )));
        Dish dish2 = new Dish("Red Lentil Soup with Chicken and Turnips", new Recipe(List.of(
                new Product("seasoning"),  new Product("garlic"),  new Product("soup"),
                new Product("salt and pepper"),  new Product("vegetable stock"),  new Product("onion"),
                new Product("tomato"),  new Product("fresh parsley"),  new Product("lentils"),
                new Product("chicken breast"),  new Product("celery"),  new Product("red lentils"),
                new Product("parsley"),  new Product("olive oil"),  new Product("turnip"),
                new Product("carrot")
        )));
        List<Dish> expectedDishes = List.of(dish1, dish2);
        File file = new File(path);
        String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        List<Dish> actualDishes = JSONService.GetDishes(json, 2);
        assertEquals(expectedDishes, actualDishes);
    }
}

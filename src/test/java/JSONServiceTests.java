import model.Dish;
import model.Product;
import model.Recipe;
import org.junit.jupiter.api.Test;
import service.JSONService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}

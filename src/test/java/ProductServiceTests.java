import model.Ingredient;
import org.junit.jupiter.api.Test;
import service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для сервиса, ответственного за работу с продуктами.
 */
public class ProductServiceTests {
    /**
     * Тест проверяет создание продуктов из строки.
     */
    @Test
    void getIngredientsTest() {
        List<Ingredient> productList = ProductService.getIngredients("ЯйцА,МолокО,МукА");
        List<Ingredient> expectedList = Arrays.asList(
                new Ingredient("яйца"),
                new Ingredient("молоко"),
                new Ingredient("мука")
        );

        assertEquals(productList, expectedList);
    }
}

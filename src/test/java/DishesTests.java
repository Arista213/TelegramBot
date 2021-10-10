import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.Dishes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishesTests {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        Dishes.dishesList = new ArrayList<>();
        Dishes.dishesList.add(new Dish("Яишница", Arrays.asList("яйца")));
        Dishes.dishesList.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }

    @Test
    void recipeByNameTest() {
        String dishName = "Блины";
        Dish pancakes = new Dish("Блины", Arrays.asList("мука", "яйца", "молоко"));
        Dish dish = Dishes.getDishByName(dishName);     
    }
}

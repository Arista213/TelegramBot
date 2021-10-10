import logic.cheif_cooker.Dish;
import logic.cheif_cooker.DishService;
import logic.Bot;

import logic.commands.AddRecipeByAdmin;
import logic.commands.AdminOff;
import logic.commands.AdminOn;
import logic.commands.RemoveRecipeByAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AdminModeCommandsTests {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        DishService.dishes = new ArrayList<>();
        DishService.dishes.add(new Dish("Яичница", Arrays.asList("яйца")));
        DishService.dishes.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }

    @Test
    void getAdminTest() {
        new AdminOn().process(bot);
        var isAdmin = bot.user.isAdmin();
        assertTrue(isAdmin);
    }

    @Test
    void lostAdminTest() {
        new AdminOn().process(bot);
        new AdminOff().process(bot);
        var isAdmin = bot.user.isAdmin();
        assertFalse(isAdmin);
    }

    @Test
    void addRecipeByAdminTest() {
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        getAdminMode();
        addDishByAdmin(testDishName, testIngredients);
        Dish dish = DishService.getDishByName(testDishName);
        assertEquals(testDishName, dish.name);
        assertEquals(testIngredients, dish.getRecipe());
    }

    @Test
    void addRecipeByNoAdminTest() {
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        int dishesCountBeforeAdd = DishService.dishes.size();
        addDishByAdmin(testDishName, testIngredients);
        assertEquals(dishesCountBeforeAdd, DishService.dishes.size());
    }

    private void addDishByAdmin(String dishName, String ingredients) {
        Thread thread = new Thread(() -> new AddRecipeByAdmin().process(bot));
        thread.start();
        bot.waitForOutput();
        bot.receive(dishName);
        bot.waitForOutput();
        bot.receive(ingredients);
        bot.waitForOutput();
    }

    @Test
    void removeRecipeByAdminTest() {
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        getAdminMode();
        addDishByAdmin(testDishName, testIngredients);
        int dishesCountAfterAdd = DishService.dishes.size();
        removeDishByAdmin(testDishName);
        assertEquals(dishesCountAfterAdd - 1, DishService.dishes.size());
    }

    private void getAdminMode() {
        new AdminOn().process(bot);
        bot.waitForOutput();
    }

    @Test
    void removeRecipeByNoAdminTest() {
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        addDishByAdmin(testDishName, testIngredients);
        int dishesCountAfterAdd = DishService.dishes.size();
        removeDishByAdmin(testDishName);
        assertEquals(dishesCountAfterAdd, DishService.dishes.size());
    }

    private void removeDishByAdmin(String testDishName) {
        var thread = new Thread(() -> new RemoveRecipeByAdmin().process(bot));
        thread.start();
        bot.waitForOutput();
        bot.receive(testDishName);
        bot.waitForOutput();
    }
}

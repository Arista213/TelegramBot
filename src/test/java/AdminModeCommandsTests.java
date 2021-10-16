import logic.Bot;
import logic.User;
import logic.commands.AdminOff;
import logic.commands.AdminOn;
import logic.cook.Dish;
import logic.cook.DishService;
import logic.cook.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminModeCommandsTests {
    Bot bot;
    TestCommunicateService service;

    @BeforeEach
    void setUp() {
        service = new TestCommunicateService();
        bot = new Bot(service, new User());
        DishService.dishes = new ArrayList<>();
        DishService.dishes.add(new Dish("Яичница", new Recipe(List.of("яйца"))));
        DishService.dishes.add(new Dish("Блины", new Recipe(Arrays.asList("яйца", "мука", "молоко"))));
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
        new AdminOn().process(bot);
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        addDishByAdmin(testDishName, testIngredients);
        Dish dish = DishService.getDishByTitle(testDishName);
        assertEquals(testDishName, dish.title);
        assertEquals(testIngredients, dish.getRecipe().toString());
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
        service.setInstruction(Arrays.asList(
                "/admin_add_recipe",
                dishName,
                ingredients
        ));
        service.runInstruction(bot);
    }

    @Test
    void removeRecipeByAdminTest() {
        new AdminOn().process(bot);
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        addDishByAdmin(testDishName, testIngredients);
        int dishesCountAfterAdd = DishService.dishes.size();
        removeDishByAdmin(testDishName);
        assertEquals(dishesCountAfterAdd - 1, DishService.dishes.size());
    }

    @Test
    void removeRecipeByNoAdminTest() {
        String testDishName = "тестовая котлета";
        String testIngredients = "ингредиент_1 ингредиент_2";
        int dishesCountBeforeAdd = DishService.dishes.size();
        addDishByAdmin(testDishName, testIngredients);
        removeDishByAdmin(testDishName);

        assertEquals(dishesCountBeforeAdd, DishService.dishes.size());
    }

    private void removeDishByAdmin(String testDishName) {
        service.setInstruction(Arrays.asList(
                "/admin_remove_recipe",
                testDishName
        ));
        service.runInstruction(bot);
    }
}

import logic.cheif_cooker.Dish;
import logic.cheif_cooker.DishService;
import logic.Bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class BotTests {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        DishService.dishes = new ArrayList<>();
        DishService.dishes.add(new Dish("Яишница", Arrays.asList("яйца")));
        DishService.dishes.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }

    @Test
    void botStartTest() {
        bot.receive("/start");
        var answer = bot.waitForOutput();
        String expected = "Шеф-повар здесь!\nНапиши мне /help и я расскажу, что умею\n";
        assertEquals(expected, answer);
    }

    @Test
    void botUnknownCommandTest() {
        bot.receive("Неизвестная комманда");
        var answer = bot.waitForOutput();
        String expected = "Неизвестная комманда";
        assertEquals(expected, answer);
    }


    @Test
    void botRecipeByNameTest() {
        bot.receive("/recipe_name");
        String answer = bot.waitForOutput();
        String expected = "Введите название блюда, которое вы хотите приготовить";
        assertEquals(expected, answer);

        bot.receive("Яишница");
        answer = bot.waitForOutput();
        expected = "яйца";
        assertEquals(expected, answer);
    }

    @Test
    void botRecipeByIngredientsTest() {
        bot.receive("/recipe_ingredients");
        String answer = bot.waitForOutput();
        String expected = "Введите ингредиенты, которые у вас имеются";
        assertEquals(expected, answer);

        bot.receive("яйца молоко мука");
        answer = bot.waitForOutput();
        expected = "Можно приготовить: Яишница\n" +
                "яйца\n" +
                "Можно приготовить: Блины\n" +
                "яйца мука молоко\n";
        assertEquals(expected, answer);
    }

    @Test
    void getAdminTest() {
        bot.receive("/admin_on");
        String answer = bot.waitForOutput();
        String expected = "Теперь вы администратор этого сервера-сервиса dxdxdxd";
        assertEquals(expected, answer);
    }

    @Test
    void lostAdminTest() {
        bot.receive("/admin_on");
        bot.waitForOutput();
        bot.receive("/admin_off");
        String answer = bot.waitForOutput();
        String expected = "Админом больше, админом меньше, какая разница.... ;DDDDDDDD";
        assertEquals(expected, answer);
    }

    @Test
    void addRecipeByAdminTest() {
        bot.receive("/admin_on");
        bot.waitForOutput();
        bot.receive("/admin_add_recipe");
        bot.waitForOutput();
        bot.receive("тестовая котлета");
        bot.waitForOutput();
        bot.receive("ингредиент_1 ингредиент_2");
        bot.waitForOutput();
        bot.receive("/recipe_name");
        bot.waitForOutput();
        bot.receive("тестовая котлета");
        String answer = bot.waitForOutput();
        String expected = "ингредиент_1 ингредиент_2";
        assertEquals(expected, answer);
    }

    @Test
    void removeRecipeByAdminTest() {
        bot.receive("/admin_on");
        bot.waitForOutput();
        bot.receive("/admin_add_recipe");
        bot.waitForOutput();
        bot.receive("тестовая котлета");
        bot.waitForOutput();
        bot.receive("ингредиент_1 ингредиент_2");
        bot.waitForOutput();
        bot.receive("/admin_remove_recipe");
        bot.waitForOutput();
        bot.receive("тестовая котлета");
        bot.waitForOutput();
        bot.receive("/recipe_name");
        bot.waitForOutput();
        bot.receive("тестовая котлета");
        String answer = bot.waitForOutput();
        String expected = "К сожалению блюдо не найдено(";
        assertEquals(expected, answer);
    }
}
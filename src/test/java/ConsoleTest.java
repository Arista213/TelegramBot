import logic.cheif_cooker.Dish;
import logic.cheif_cooker.Dishes;
import logic.Bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsoleTest {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        Dishes.dishesList = new ArrayList<>();
        Dishes.dishesList.add(new Dish("Яишница", Arrays.asList("яйца")));
        Dishes.dishesList.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }

    @Test
    void botStartTest() {
        bot.receive("/start");
        var answer = bot.waitForOutput();
        String expected = "Шеф-повар здесь!\n" +
                "\n" +
                "Вот то, что я умею делать бот\n" +
                "/recipe_name - рецепт блюда по его названию\n" +
                "/recipe_ingredients - рецепт блюд, которые можно приготовить при имеющихся ингредиентах\n";
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
        String excepted = "Введите название блюда, которое вы хотите приготовить";
        assertEquals(excepted, answer);

        bot.receive("Яишница");
        answer = bot.waitForOutput();
        excepted = "\n------Рецепт------\t\n" +
                "яйца\n" +
                "------------------\n";
        assertEquals(excepted, answer);
    }

    @Test
    void botRecipeByIngredientsTest() {
        bot.receive("/recipe_ingredients");
        String answer = bot.waitForOutput();
        String excepted = "Введите ингредиенты, которые у вас имеются";
        assertEquals(excepted, answer);

        bot.receive("яйца молоко мука");
        answer = bot.waitForOutput();
        excepted = "Можно приготовить: Яишница\n" +
                "------Рецепт------\t\n" +
                "яйца\n" +
                "------------------\n" +
                "\n" +
                "Можно приготовить: Блины\n" +
                "------Рецепт------\t\n" +
                "яйца\n" +
                "мука\n" +
                "молоко\n" +
                "------------------\n" +
                "\n";
        assertEquals(excepted, answer);
    }
}
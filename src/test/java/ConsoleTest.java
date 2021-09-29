import brain.cheif_cooker.Dish;
import com.google.gson.Gson;
import brain.Bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConsoleTest {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
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
        bot.receive("Unknown Command");
        var answer = bot.waitForOutput();
        String expected = "Unknown command!";
        assertEquals(expected, answer);
    }

    @Test
    public void givenListOfMyClass_whenSerializing_thenCorrect() {
        Dish friedEggs = new Dish("Яишница", Arrays.asList("яйца"));
        Dish pancakes = new Dish("Блины", Arrays.asList("яйца", "тесто", "молоко"));

        List<Dish> list = Arrays.asList(pancakes, friedEggs);

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        String expectedString = "[{\"name\":\"Блины\",\"recipe\":[\"яйца\",\"тесто\"" +
                ",\"молоко\"]},{\"name\":\"Яишница\",\"recipe\":[\"яйца\"]}]";

        assertEquals(expectedString, jsonString);
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
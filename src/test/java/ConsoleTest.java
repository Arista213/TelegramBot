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
        Dish friedEggs = new Dish("Яишница", new HashMap<>() {{
            put("яйца", "2 шт");
        }});

        Dish pancakes = new Dish("Блины", new HashMap<>() {{
            put("яйца", "3 шт");
            put("тесто", "300г");
            put("молоко", "500мл");
        }});

        List<Dish> list = Arrays.asList(pancakes, friedEggs);

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        String expectedString = "[{\"name\":\"Блины\",\"recipe\":" +
                "{\"тесто\":\"300г\",\"молоко\":\"500мл\"," +
                "\"яйца\":\"3 шт\"}}," +
                "{\"name\":\"Яишница\",\"recipe\":{\"яйца\":\"2 шт\"}}]";

        assertEquals(expectedString, jsonString);
    }

    void botRecipeByNameTest() {
        bot.receive("/recipe_name");
        String answer = bot.waitForOutput();
        String excepted = "Введите название блюда, которое вы хотите приготовить";
        assertEquals(excepted, answer);

        bot.receive("Яишница");
        answer = bot.waitForOutput();
        excepted = "\n" +
                "------Рецепт------\t\n" +
                "яйца\t-\t2 шт\n" +
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
                "яйца\t-\t2 шт\n" +
                "------------------\n" +
                "\n" +
                "Можно приготовить: Блины\n" +
                "------Рецепт------\t\n" +
                "мука\t-\t300г\n" +
                "молоко\t-\t500мл\n" +
                "яйца\t-\t3 шт\n" +
                "------------------\n\n";
        assertEquals(excepted, answer);
    }
}
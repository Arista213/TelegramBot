import Functionality.Dish;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConsoleTest {

    Bot bot;
    BufferedReader br;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        bot.start();
    }

    @Test
    void botStartTest() {
        var answer = bot.receive("/start");
        String expected = "Bot has started!\n" +
                "\n" +
                "Here's what bot can do\n" +
                "/start - starts bot\n" +
                "/hello - bot says hello\n";
        assertEquals(expected, answer);
    }

    @Test
    void botHelloTest() {
        var answer = bot.receive("/hello");
        String expected = "Bot says hello!";
        assertEquals(expected, answer);
    }

    @Test
    void botUnknownCommandTest() {
        var answer = bot.receive("Unknown Command");
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
}
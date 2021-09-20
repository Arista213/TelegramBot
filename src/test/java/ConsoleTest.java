import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;

public class ConsoleTest {

    Bot bot;
    BufferedReader br;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        bot.Start();
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
}
import brain.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;

public class ConsoleTest {
    Bot bot;

    @BeforeEach
    void setUp() {
        bot = new Bot();
        bot.start();
    }

    @Test
    void botStartTest() {
        bot.receive("/start");
        var answer = waitForAnswer();
        String expected = "Bot has started!\n" +
                "\n" +
                "Here's what bot can do\n" +
                "/start - starts bot\n" +
                "/hello - bot says hello\n";
        assertEquals(expected, answer);
    }

    @Test
    void botHelloTest() {
        bot.receive("/hello");
        var answer = waitForAnswer();
        String expected = "Bot says hello!";
        assertEquals(expected, answer);
    }

    @Test
    void botUnknownCommandTest() {
        bot.receive("Unknown Command");
        var answer = waitForAnswer();
        String expected = "Unknown command!";
        assertEquals(expected, answer);
    }

    String waitForAnswer() {
        String answer = null;
        while (answer == null) {
            answer = bot.getOutput();
        }
        return answer;
    }
}
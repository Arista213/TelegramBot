import Commands.Command;
import Commands.Hello;
import Commands.Start;

import java.util.*;

public class Bot {
    private Map<String, Command> commands;

    public void Start() {
        initCommands();
    }

    /**
     * Здесь происходит заполение словоря коммандами
     */
    private void initCommands() {
        commands = new HashMap<>();
        commands.put("/start", new Start());
        commands.put("/hello", new Hello());
    }

    public String receive(String message) {
        if (!commands.containsKey(message)) {
            return "Unknown command!";
        }

        var c = commands.get(message);
        var a = c.getClass();
        return c.process();
    }
}

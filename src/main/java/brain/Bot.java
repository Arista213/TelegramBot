package brain;

import brain.commands.*;
import brain.commands.RecipeByName;
import brain.commands.Start;

import java.util.*;

public class Bot {
    public User user = new User();
    private Map<String, Command> commands;

    private String input = null;
    private String output = null;

    public boolean commandInRunning;

    public Bot() {
        initCommands();

        Thread thread = new Thread(this::startListening);
        thread.start();
    }

    /**
     * Здесь происходит заполение словоря коммандами
     */
    private void initCommands() {
        commands = new HashMap<>();
        commands.put("/start", new Start());
        commands.put("/recipe", new RecipeByName());
        commands.put("/recipe_name", new RecipeByName());
        commands.put("/recipe_ingredients", new RecipeByIngredients());
        commands.put("/admin_on", new AdminOn());
        commands.put("/admin_off", new AdminOff());
        commands.put("1", new PutRecipeByAdmin());
        commands.put("2", new RemoveRecipeByAdmin());
    }

    private void startListening() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!commandInRunning) {
                if (input != null)
                    runCommand();
            }
        }
    }

    public void receive(String input) {
        this.input = input;
    }

    private void runCommand() {
        if (!commands.containsKey(input)) {
            setOutput("Unknown command!");
            inputRead();
            return;
        }

        var c = commands.get(input);
        Thread thread = new Thread(() -> c.process(this));
        thread.start();
        inputRead();
    }

    public synchronized void setOutput(String output) {
        this.output = output;
    }

    private void outputRead() {
        output = null;
    }

    private void inputRead() {
        input = null;
    }

    public String waitForInput() {
        while (input == null) Thread.onSpinWait();
        var tempInput = input;
        inputRead();
        return tempInput;
    }

    public String waitForOutput() {
        while (output == null) Thread.onSpinWait();
        var tempOutput = output;
        outputRead();
        return tempOutput;
    }
}

package logic;

import logic.commands.*;
import logic.commands.RecipeByName;
import logic.commands.Start;

public class Bot {
    public User user = new User();

    private volatile String input = null;
    private volatile String output = null;

    public Bot() {
        Thread thread = new Thread(this::startListening);
        thread.start();
    }

    /**
     * Здесь происходит заполение словоря коммандами
     */
    private void runCommand() {
        ICommand command = switch (input) {
            case "/start" -> new Start();
            case "/recipe_name" -> new RecipeByName();
            case "/recipe_ingredients" -> new RecipeByIngredients();
            case "/admin_on" -> new AdminOn();
            case "/admin_off" -> new AdminOff();
            case "/admin_add_recipe" -> new AddRecipeByAdmin();
            case "/admin_remove_recipe" -> new RemoveRecipeByAdmin();
            case "/help" -> new Help();
            default -> new UnknownCommand();
        };

        inputRead();
        command.process(this);
    }

    private void startListening() {
        while (!Thread.currentThread().isInterrupted()) {
            if (input != null)
                runCommand();
        }
    }

    public void receive(String input) {
        this.input = input;
    }

    public synchronized void setOutput(String output) {
        this.output = output;
    }

    private synchronized String outputRead() {
        String tempOutput = output;
        output = null;
        return tempOutput;
    }

    private synchronized String inputRead() {
        String tempInput = input;
        input = null;
        return tempInput;
    }

    public String waitForInput() {
        while (input == null) Thread.onSpinWait();
        return inputRead();
    }

    public String waitForOutput() {
        while (output == null) Thread.onSpinWait();
        return outputRead();
    }
}

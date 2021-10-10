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
     * Обработка комманды
     */
    private void runCommand() {
        ICommand command = switch (input) {
            case "/start" -> new Start();
            case "/help" -> new Help();
            case "/recipe_name" -> new RecipeByName();
            case "/recipe_ingredients" -> new RecipeByIngredients();
            case "/admin_on" -> new AdminOn();
            case "/admin_off" -> new AdminOff();
            case "/admin_add_recipe" -> new AddRecipeByAdmin();
            case "/admin_remove_recipe" -> new RemoveRecipeByAdmin();
            default -> new UnknownCommand();
        };

        inputRead();
        command.process(this);
    }

    /**
     * Бесконечный цикл, в котором бот обрабатывает ввод пользователя.
     */
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

    /**
     * Ждёт ввода от пользователя
     */
    public String waitForInput() {
        while (input == null) Thread.onSpinWait();
        return inputRead();
    }

    public String waitForOutput() {
        while (output == null) Thread.onSpinWait();
        return outputRead();
    }
}

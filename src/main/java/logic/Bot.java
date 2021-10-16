package logic;

import logic.commands.*;
import logic.commands.RecipeByName;
import logic.commands.Start;
import logic.communication.ICommunicateService;
import logic.communication.Message;

public class Bot {
    public final User user;

    private String input;

    public final ICommunicateService communicateService;

    public Bot(ICommunicateService communicateService, User user) {
        this.communicateService = communicateService;
        this.user = user;
    }

    public String getInput() {
        return input;
    }

    public void setOutput(String output) {
        communicateService.sendMessage(new Message(output));
    }

    /**
     * Получить сообщение из сервиса, который принимает ввод пользователя
     */
    public void requestInput() {
        input = communicateService.getMessage().text;
    }

    /**
     * Запуск комманды
     */
    public void runCommand(String input) {
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

        command.process(this);
    }
}

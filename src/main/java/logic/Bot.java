package logic;

import logic.commands.*;
import logic.commands.RecipeByName;
import logic.commands.Start;
import logic.communication.ICommunicateService;
import logic.communication.Message;

public class Bot {
    public final User user;

    public final ICommunicateService communicateService;

    public Bot(ICommunicateService communicateService, User user) {
        this.communicateService = communicateService;
        this.user = user;
    }

    /**
     * Отправить сообщению сервису, который выводит данные пользователю.
     */
    public void setOutput(String output) {
        communicateService.sendMessage(new Message(output));
    }

    /**
     * Получить сообщение из сервиса, который принимает ввод пользователя.
     */
    public String requestInput() {
        return communicateService.getMessage().text;
    }

    /**
     * Запуск комманды.
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

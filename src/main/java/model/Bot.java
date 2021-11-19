package model;

import api.DishApi;
import commands.*;
import message.Message;
import message.MessageProvider;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Бот.
 */
public class Bot {
    private final MessageProvider messageProvider;

    private final Map<String, ICommand> commands = new HashMap<>();

    private final ICommand UNKNOWN_COMMAND = new UnknownCommand(this);

    public Bot(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
        fillCommands();
        DishApi.initiateDefault();
    }

    /**
     * Заполняет словарь комманд.
     */
    private void fillCommands() {
        commands.put("/start", new Start(this));
        commands.put("/help", new Help(this));
        commands.put("/dish_by_title", new DishByTitle(this));
        commands.put("/dishes_by_title", new DishByProducts(this));
        commands.put("/admin_mode", new AdminMode(this));
        commands.put("/user_mode", new UserMode(this));
        commands.put("/add_dish", new AddDishByAdmin(this));
        commands.put("/remove_dish", new RemoveDishByAdmin(this));
    }

    /**
     * Отправить сообщению классу реализующего абстракцию MessageProvider чтобы отправить данные пользователю.
     */
    public void setOutput(String output) {
        messageProvider.sendMessage(new Message(output));
    }

    /**
     * Получить сообщение из класса реализующего абстракцию MessageProvider чтобы принять ввод пользователя.
     */
    public String requestInput() {
        return messageProvider.getMessage().getText();
    }

    /**
     * Запуск комманды.
     */
    public void runCommand(String input, User user) {
        ICommand command = commands.containsKey(input)
                ? commands.get(input.toLowerCase(Locale.ROOT))
                : UNKNOWN_COMMAND;

        command.process(user);
    }
}

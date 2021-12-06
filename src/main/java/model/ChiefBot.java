package model;

import commands.*;
import dao.DishDao;
import message.MessageWaiter;
import message.model.IMessageProvider;
import message.model.Message;
import service.DishService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Шеф Бот.
 */
public final class ChiefBot implements IBot {
    private final IMessageProvider provider;

    private final Map<String, ICommand> commands = new HashMap<>();

    private final ICommand UNKNOWN_COMMAND = new UnknownCommand(this);

    private final DishService dishService;

    private final DishDao dishDao;

    public ChiefBot(IMessageProvider provider, DishDao dishDao, DishService dishService) {
        this.provider = provider;
        this.dishService = dishService;
        this.dishDao = dishDao;
        fillCommands();
    }

    /**
     * Заполняет словарь комманд.
     */
    private void fillCommands() {
        commands.put("/start", new Start(this));
        commands.put("/help", new Help(this));
        commands.put("/dish_by_title", new DishByTitle(this));
        commands.put("/dishes_by_products", new DishesByProducts(this));
        commands.put("/admin_mode", new AdminMode(this));
        commands.put("/user_mode", new UserMode(this));
        commands.put("/add_dish", new AddDishByAdmin(this));
        commands.put("/remove_dish", new RemoveDishByAdmin(this));
        commands.put("/save_dishes", new SaveDishes(this));
        commands.put("/load_dishes", new LoadDishes(this));
    }

    /**
     * Отправить сообщению классу реализующего абстракцию MessageProvider чтобы отправить данные пользователю.
     */
    public void setOutput(User user, String output) {
        provider.sendMessage(user, new Message(output));
    }

    /**
     * Запуск комманды.
     */
    @Override
    public void handleMessage(User user, Message message) {
        MessageWaiter mw = user.getMessageWaiter();
        if (mw.isWaiting()) {
            mw.execute(user, message);
        } else {
            String input = message.getText();
            ICommand command = commands.containsKey(input)
                    ? commands.get(input.toLowerCase(Locale.ROOT))
                    : UNKNOWN_COMMAND;

            command.process(user);
        }
    }

    public DishService getDishService() {
        return dishService;
    }

    public DishDao getDishDao() {
        return dishDao;
    }
}

package model;

import IO.provider.IMessageProvider;
import IO.waiter.MessageWaiter;
import commands.*;
import dao.DishDao;
import service.DishService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static constants.Commands.*;

/**
 * Шеф Бот.
 */
public final class ChiefBot implements IBot
{
    private final IMessageProvider provider;

    private final Map<String, ICommand> commands = new HashMap<>();

    private final ICommand UNKNOWN_COMMAND = new UnknownCommand(this);

    private final DishService dishService;

    private final DishDao dishDao;

    public ChiefBot(IMessageProvider provider, DishDao dishDao, DishService dishService)
    {
        this.provider = provider;
        this.dishService = dishService;
        this.dishDao = dishDao;
        fillCommands();
    }

    /**
     * Заполняет словарь комманд.
     */
    private void fillCommands()
    {
        commands.put(START.toLowerCaseValue(), new Start(this));
        commands.put(HELP.toLowerCaseValue(), new Help(this));
        commands.put(DISH_BY_TITLE.toLowerCaseValue(), new DishByTitle(this));
        commands.put(DISHES_BY_PRODUCTS.toLowerCaseValue(), new DishesByProducts(this));
        commands.put(ADMIN_MODE.toLowerCaseValue(), new AdminMode(this));
        commands.put(USER_MODE.toLowerCaseValue(), new UserMode(this));
        commands.put(ADD_DISH.toLowerCaseValue(), new AddDishByAdmin(this));
        commands.put(REMOVE_DISH.toLowerCaseValue(), new RemoveDishByAdmin(this));
        commands.put(SAVE_DISHES.toLowerCaseValue(), new SaveDishes(this));
        commands.put(LOAD_DISHES.toLowerCaseValue(), new LoadDishes(this));
    }

    /**
     * Отправить сообщению классу реализующего абстракцию MessageProvider чтобы отправить данные пользователю.
     */
    public void setOutput(User user, Message output)
    {
        provider.sendMessage(user, output);
    }

    /**
     * Запуск комманды.
     */
    @Override
    public void handleMessage(User user, Message message)
    {
        MessageWaiter mw = user.getMessageWaiter();
        if (mw.isWaiting())
        {
            mw.execute(message);
        }
        else
        {
            String input = message.getText().toLowerCase();
            ICommand command = commands.containsKey(input)
                    ? commands.get(input.toLowerCase(Locale.ROOT))
                    : UNKNOWN_COMMAND;

            command.process(user);
        }
    }

    public DishService getDishService()
    {
        return dishService;
    }

    public DishDao getDishDao()
    {
        return dishDao;
    }
}

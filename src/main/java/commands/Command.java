package commands;

import dao.DishDao;
import models.ChiefBot;
import services.APIService;
import services.DishService;
import services.UserService;

/**
 * Абстракция команды.
 */
public abstract class Command implements ICommand {
    protected final ChiefBot bot;
    protected final UserService userService;
    protected final DishService dishService;
    protected final DishDao dishDao;
    protected final APIService apiService;

    public Command(ChiefBot bot) {
        this.bot = bot;
        userService = bot.getUserService();
        dishService = bot.getDishService();
        dishDao = bot.getDishDao();
        apiService = bot.getApiService();
    }
}

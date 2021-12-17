package commands;

import constants.Config;
import model.ChiefBot;
import model.User;
import service.JSONService;

/**
 * Сохранить блюда в json.
 */
public class SaveDishes extends Command
{
    public SaveDishes(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        JSONService.saveDishes(bot.getDishDao().getAll(), Config.JSON_DISHES_PATH.toStringValue());
    }
}

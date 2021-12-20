package commands;

import constants.Config;
import model.ChiefBot;
import model.User;
import service.JsonService;

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
        JsonService.saveDishes(dishDao.getAll(), Config.JSON_DISHES_PATH.toStringValue());
    }
}

package commands;

import constants.Config;
import models.ChiefBot;
import models.User;
import services.JsonService;

/**
 * Сохранить блюда в json.
 */
public class SaveDishes extends Command {
    public SaveDishes(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        JsonService.saveDishes(dishDao.getAll(), Config.JSON_DISHES_PATH.toStringValue());
    }
}

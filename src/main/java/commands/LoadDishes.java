package commands;

import constants.Config;
import model.ChiefBot;
import model.Dish;
import model.User;
import service.JsonService;

import java.util.List;

/**
 * Загрузить блюда из json.
 */
public class LoadDishes extends Command
{
    public LoadDishes(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        List<Dish> dishes = JsonService.loadDishes(Config.JSON_DISHES_PATH.toStringValue());
        dishes.forEach(dishDao::save);
    }
}

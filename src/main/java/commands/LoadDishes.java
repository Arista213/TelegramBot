package commands;

import api.DishApi;
import constants.Configuration;
import model.Bot;
import model.Dish;
import model.User;
import service.JSONService;

import java.util.List;

/**
 * Загрузить блюда из json.
 */
public class LoadDishes extends Command {
    public LoadDishes(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        List<Dish> dishes = JSONService.loadDishes(Configuration.JSON_DISHES_PATH.toStringValue());
        DishApi.initiate(dishes);
    }
}

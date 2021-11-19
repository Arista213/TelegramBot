package commands;

import api.DishApi;
import constants.Configuration;
import model.Bot;
import model.User;
import service.JSONService;

/**
 * Сохранить блюда в json.
 */
public class SaveDishes extends Command {
    public SaveDishes(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        System.out.println(DishApi.getAll());
        JSONService.saveDishes(DishApi.getAll(), Configuration.JSON_DISHES_PATH.toStringValue());
    }
}

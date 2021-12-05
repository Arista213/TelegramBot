package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.User;
import service.APIService;

import java.util.List;

/**
 * Бот выводит рецепт по названию блюда.
 */
public class DishByTitle extends Command {
    public DishByTitle(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, Commands.DISH_TITLE.toStringValue());
        UserApi.addToMessageWaiter(user, this::getDishByTitle);
    }

    private void getDishByTitle(User user, Message message) {
        String dishTitle = message.getText();
        Dish dish;
        for (int i = 0; i < 5; i++) {
            dish = APIService.getDishByTitle(dishTitle);
            if (dish != null) {
                String output = DishApi.getStringFromDishes(List.of(dish));
                bot.setOutput(user, output);
                return;
            }
        }

        dish = DishApi.findDishByTitle(dishTitle);

        bot.setOutput(user, dish != null
                ? dish.toString()
                : Commands.DISH_IS_NOT_FOUND.toStringValue());
    }
}

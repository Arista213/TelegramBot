package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import constants.Numbers;
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
        bot.setOutput(user, new Message(Commands.DISH_TITLE.toStringValue()));
        UserApi.addToMessageWaiter(user, this::dishByTitle);
    }

    /**
     * Выводит пользователю блюдо и его рецепт.
     */
    private void dishByTitle(User user, Message message) {
        String dishTitle = message.getText();
        Dish dish;
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++) {
            dish = APIService.getDishByTitle(dishTitle);
            if (dish != null) {
                Message output = new Message(DishApi.getStringFromDishes(List.of(dish)));
                output.setImageURL(dish.getImageUrl());
                bot.setOutput(user, output);
                return;
            }
        }

        dish = DishApi.findDishByTitle(dishTitle);

        bot.setOutput(user, dish != null
                ? new Message(dish.toString())
                : new Message(Commands.DISH_IS_NOT_FOUND.toStringValue()));
    }
}

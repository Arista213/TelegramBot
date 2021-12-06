package commands;

import constants.CommandsOutput;
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
        bot.setOutput(user, CommandsOutput.DISH_TITLE.toStringValue());
        user.addMessageWait(this::dishByTitle);
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
                String output = bot.getDishService().getStringFromDishes(List.of(dish));
                bot.setOutput(user, output);
                return;
            }
        }

        dish = bot.getDishService().findDishByTitle(dishTitle);

        bot.setOutput(user, dish != null
                ? dish.toString()
                : CommandsOutput.DISH_IS_NOT_FOUND.toStringValue());
    }
}

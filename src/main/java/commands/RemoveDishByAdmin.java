package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.User;

/**
 * Удалить блюдо, если в режиме администратора.
 */
public class RemoveDishByAdmin extends Command {
    public RemoveDishByAdmin(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        if (!UserApi.isAdmin(user)) {
            bot.setOutput(user, Commands.NOT_ENOUGH_RIGHTS.toStringValue());
            return;
        }

        UserApi.addToMessageWaiter(user, this::removeDishByName);
    }

    private void removeDishByName(User user, Message message) {
        bot.setOutput(user, Commands.DISH_TITLE_TO_REMOVE.toStringValue());
        String dishName = message.getText();
        Dish dish = DishApi.findDishByTitle(dishName);
        if (dish != null) {
            DishApi.remove(dishName);
            bot.setOutput(user, Commands.DISH_REMOVED.toStringValue());
        } else
            bot.setOutput(user, Commands.DISH_IS_NOT_FOUND.toStringValue());
    }
}

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
            bot.setOutput(user, new Message(Commands.NOT_ENOUGH_RIGHTS.toStringValue()));
            return;
        }

        bot.setOutput(user, new Message(Commands.DISH_TITLE_TO_REMOVE.toStringValue()));
        UserApi.addToMessageWaiter(user, this::removeDishByName);
    }

    /**
     * По полученному названию блюда удалить его из базы данных.
     */
    private void removeDishByName(User user, Message message) {
        String dishName = message.getText();
        Dish dish = DishApi.findDishByTitle(dishName);
        if (dish != null) {
            DishApi.remove(dishName);
            bot.setOutput(user, new Message(Commands.DISH_REMOVED.toStringValue()));
        } else
            bot.setOutput(user, new Message(Commands.DISH_IS_NOT_FOUND.toStringValue()));
    }
}

package commands;

import constants.Commands;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.Mode;
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
        if (user.getMode() == Mode.User) {
            bot.setOutput(user, Commands.NOT_ENOUGH_RIGHTS.toStringValue());
            return;
        }

        bot.setOutput(user, Commands.DISH_TITLE_TO_REMOVE.toStringValue());
        user.addMessageWait(this::removeDishByName);
    }

    /**
     * По полученному названию блюда удалить его из базы данных.
     */
    private void removeDishByName(User user, Message message) {
        Dish dish = bot.getDishService().findDishByTitle(message.getText());
        if (dish != null) {
            bot.getDishDao().delete(dish.title);
            bot.setOutput(user, Commands.DISH_REMOVED.toStringValue());
        } else
            bot.setOutput(user, Commands.DISH_IS_NOT_FOUND.toStringValue());
    }
}

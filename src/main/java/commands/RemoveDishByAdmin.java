package commands;

import constants.CommandsOutput;
import model.*;

/**
 * Удалить блюдо, если в режиме администратора.
 */
public class RemoveDishByAdmin extends Command
{
    public RemoveDishByAdmin(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        if (user.getMode() == Mode.User)
        {
            bot.setOutput(user, new Message(CommandsOutput.NOT_ENOUGH_RIGHTS.toStringValue()));
            return;
        }

        bot.setOutput(user, new Message(CommandsOutput.DISH_TITLE_TO_REMOVE.toStringValue()));
        user.addMessageWait(this::removeDishByName);
    }

    /**
     * По полученному названию блюда удалить его из базы данных.
     */
    private void removeDishByName(User user, Message message)
    {
        Dish dish = bot.getDishService().findDishByTitle(message.getText());
        if (dish != null)
        {
            bot.getDishDao().delete(dish.getTitle());
            bot.setOutput(user, new Message(CommandsOutput.DISH_REMOVED.toStringValue()));
        }
        else
            bot.setOutput(user, new Message(CommandsOutput.DISH_IS_NOT_FOUND.toStringValue()));
    }
}

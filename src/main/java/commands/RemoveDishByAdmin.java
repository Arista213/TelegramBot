package commands;

import api.DishApi;
import api.UserApi;
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
            bot.setOutput(user, "У вас недостаточно прав");
            return;
        }

        UserApi.addToMessageWaiter(user, this::removeDishByName);
    }

    private void removeDishByName(User user, Message message) {
        bot.setOutput(user, "Введите название блюда, которое вы хотите удалить");
        String dishName = message.getText();
        Dish dish = DishApi.findDishByTitle(dishName);
        if (dish.isExist) {
            DishApi.remove(dishName);
            bot.setOutput(user, "Блюдо удалено воуоуоу");
        } else
            bot.setOutput(user, "Такого блюда в базе данных не нашлось");
    }
}

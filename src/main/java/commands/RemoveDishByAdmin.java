package commands;

import api.DishApi;
import model.Bot;
import model.Dish;

/**
 * Удалить блюдо, если в режиме администратора.
 */
public class RemoveDishByAdmin extends Command {
    public RemoveDishByAdmin(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        if (!bot.getUser().isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы хотите удалить");
        String dishName = bot.requestInput();
        Dish dish = DishApi.findDishByTitle(dishName);
        if (dish.isExist) {
            DishApi.remove(dishName);
            bot.setOutput("Блюдо удалено воуоуоу");
        } else
            bot.setOutput("Такого блюда в базе данных не нашлось");
    }
}
package brain.commands;

import brain.Bot;
import brain.cheif_cooker.Dish;
import brain.cheif_cooker.Dishes;

public class RemoveRecipeByAdmin implements ICommand {
    public void process(Bot bot) {
        if (!bot.user.isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы хотите удалить");
        String dishName = bot.waitForInput();
        Dish dish = Dish.findDishByName(Dishes.list, dishName);
        if (dish != null) {
            Dishes.removeDish(dishName);
            bot.setOutput("Блюдо удалено воуоуоу");
        } else
            bot.setOutput("Такого блюда в базе данных не нашлось");
    }
}

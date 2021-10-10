package logic.commands;

import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.DishService;

public class RemoveRecipeByAdmin implements ICommand {
    public void process(Bot bot) {
        if (!bot.user.isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы хотите удалить");
        String dishName = bot.waitForInput();
        Dish dish = DishService.getDishByName(dishName);
        if (dish != null) {
            DishService.removeDish(dishName);
            bot.setOutput("Блюдо удалено воуоуоу");
        } else
            bot.setOutput("Такого блюда в базе данных не нашлось");
    }
}

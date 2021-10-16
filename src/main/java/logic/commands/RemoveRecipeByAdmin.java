package logic.commands;

import logic.Bot;
import logic.cook.Dish;
import logic.cook.DishService;

public class RemoveRecipeByAdmin implements ICommand {
    public void process(Bot bot) {
        if (!bot.user.isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы хотите удалить");
        bot.requestInput();
        String dishName = bot.getInput();
        Dish dish = DishService.getDishByTitle(dishName);
        if (dish.isExist) {
            DishService.removeDish(dishName);
            bot.setOutput("Блюдо удалено воуоуоу");
        } else
            bot.setOutput("Такого блюда в базе данных не нашлось");
    }
}

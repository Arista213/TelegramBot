package logic.commands;

import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.DishService;

/**
 * Бот выводит рецепт по названию блюда
 */
public class RecipeByName implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        String dishName = bot.waitForInput();

        Dish dish = DishService.getDishByName(dishName);

        bot.setOutput(!dish.name.equals("")
                ? DishService.getDishByName(dishName).getRecipe()
                : "К сожалению блюдо не найдено(");
    }
}

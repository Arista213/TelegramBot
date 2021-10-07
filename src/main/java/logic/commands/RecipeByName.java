package logic.commands;

import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.Dishes;

/**
 * Бот выводит рецепт по названию блюда
 */
public class RecipeByName implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        String dishName = bot.waitForInput();

        Dish dish = Dishes.getDishByName(dishName);

        bot.setOutput(dish != null
                ? Dishes.getDishByName(dishName).getRecipe()
                : "К сожалению блюдо не найдено(");
    }
}

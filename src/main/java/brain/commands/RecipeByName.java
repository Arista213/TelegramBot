package brain.commands;

import brain.Bot;
import brain.cheif_cooker.Dish;
import brain.cheif_cooker.Dishes;

/**
 * Бот выводит рецепт по названию блюда
 */
public class RecipeByName implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        String dishName = bot.waitForInput();

        Dish dish = Dish.findDishByName(Dishes.list, dishName);

        bot.setOutput(dish != null
                ? Dish.findDishByName(Dishes.list, dishName).getRecipe()
                : "К сожалению блюдо не найдено(");
    }
}

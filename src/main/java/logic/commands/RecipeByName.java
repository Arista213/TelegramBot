package logic.commands;

import logic.Bot;
import logic.cook.Dish;
import logic.cook.DishService;

/**
 * Бот выводит рецепт по названию блюда
 */
public class RecipeByName implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        bot.requestInput();
        String dishName = bot.getInput();

        Dish dish = DishService.getDishByTitle(dishName);

        bot.setOutput(dish.isExist
                ? DishService.getDishByTitle(dishName).getRecipe().toString()
                : "К сожалению блюдо не найдено(");
    }
}

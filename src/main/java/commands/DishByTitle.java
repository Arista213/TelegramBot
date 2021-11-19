package commands;

import api.DishApi;
import model.Bot;
import model.Dish;
import model.User;

/**
 * Бот выводит рецепт по названию блюда.
 */
public class DishByTitle extends Command {
    public DishByTitle(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        String dishName = bot.requestInput();

        Dish dish = DishApi.findDishByTitle(dishName);

        bot.setOutput(dish.isExist
                ? DishApi.findDishByTitle(dishName).getRecipe().toString()
                : "К сожалению блюдо не найдено(");
    }
}

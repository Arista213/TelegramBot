package commands;

import api.DishApi;
import api.UserApi;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.User;

/**
 * Бот выводит рецепт по названию блюда.
 */
public class DishByTitle extends Command {
    public DishByTitle(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, "Введите название блюда, которое вы хотите приготовить");
        UserApi.addToMessageWaiter(user, this::getDishByTitle);
    }

    private void getDishByTitle(User user, Message message) {
        String dishTitle = message.getText();
        Dish dish = DishApi.findDishByTitle(dishTitle);

        bot.setOutput(user, dish.isExist
                ? DishApi.findDishByTitle(dishTitle).getRecipe().toString()
                : "К сожалению блюдо не найдено(");
    }
}

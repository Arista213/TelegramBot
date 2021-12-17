package commands;

import constants.CommandsOutput;
import constants.Numbers;
import model.telegram.Button;
import model.Message;
import model.ChiefBot;
import model.Dish;
import model.Recipe;
import model.User;
import service.APIService;

import java.util.List;

/**
 * Бот выводит рецепт по названию блюда.
 */
public class DishByTitle extends Command
{
    public DishByTitle(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        bot.setOutput(user, new Message(CommandsOutput.DISH_TITLE.toStringValue()));
        user.addMessageWait(this::dishByTitle);
    }

    /**
     * Выводит пользователю блюдо и его рецепт.
     */
    private void dishByTitle(User user, Message message)
    {
        String dishTitle = message.getText();
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++)
        {
            Dish dish = APIService.getDishByTitle(dishTitle);
            if (dish != null)
            {
                Message output = new Message(bot.getDishService().getStringFromDish(dish));
                output.setImageURL(dish.getImageUrl());
                output.setButtons(List.of(new Button("Show products", u -> showProducts(u, dish))));
                bot.setOutput(user, output);
                return;
            }
        }

        Dish dish = bot.getDishService().findDishByTitle(dishTitle);

        bot.setOutput(user, dish != null
                ? new Message(dish.toString())
                : new Message(CommandsOutput.DISH_IS_NOT_FOUND.toStringValue()));
    }

    private void showProducts(User user, Dish dish)
    {
        Recipe recipe = dish.getRecipe();
        Message output = new Message(recipe.toString());
        bot.setOutput(user, output);
    }
}

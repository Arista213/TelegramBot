package commands;

import constants.CommandsOutput;
import constants.Numbers;
import model.*;
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
        userService.addMessageWait(user, this::dishByTitle);
    }

    /**
     * Выводит пользователю блюдо и его рецепт.
     */
    private void dishByTitle(User user, Message message)
    {
        String dishTitle = message.getText();
        Dish dishFromApi = findDishFromApi(dishTitle);
        if (dishFromApi != null)
        {
            Message output = new Message(dishService.getStringFromDish(dishFromApi))
                    .setImageURL(dishFromApi.getImageUrl())
                    .setButtons(List.of(new Button("Show products", u -> showProducts(u, dishFromApi))));
            bot.setOutput(user, output);
        }
        else
        {
            Dish dishFromDao = bot.getDishService().findDishByTitle(dishTitle);
            bot.setOutput(user, dishFromDao != null
                    ? new Message(dishFromDao.toString())
                    : new Message(CommandsOutput.DISH_IS_NOT_FOUND.toStringValue()));
        }
    }

    /**
     * Попытаться с 5 попыток достать блюдо из апи.
     */
    private Dish findDishFromApi(String dishTitle)
    {
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++)
        {
            Dish dish = APIService.getDishByTitle(dishTitle);
            if (dish != null) return dish;
        }
        return null;
    }

    private void showProducts(User user, Dish dish)
    {
        Recipe recipe = dish.getRecipe();
        Message output = new Message(recipe.toString());
        bot.setOutput(user, output);
    }
}

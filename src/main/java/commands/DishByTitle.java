package commands;

import constants.CommandsOutput;
import constants.Numbers;
import models.*;

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
        Dish dishFromApi = findDishFromApi(dishTitle, user);
        if (dishFromApi != null)
        {
            Message output = new Message(dishService.getStringFromDish(dishFromApi))
                    .setImageURL(dishFromApi.getImageUrl())
                    .setButtons(List.of(List.of(
                            new Button("Show products", u -> sendProducts(u, dishFromApi)),
                            new Button("Show recipe", u -> sendRecipe(u, dishFromApi)))));
            bot.setOutput(user, output);
        }
        else
        {
            Dish dishFromDao = bot.getDishService().findDishByTitle(dishTitle);
            bot.setOutput(user, dishFromDao != null
                    ? new Message(dishService.getStringFromDish(dishFromDao))
                    .setImageURL(dishFromDao.getImageUrl())
                    .setButtons(List.of(List.of(
                            new Button("Show products", u -> sendProducts(u, dishFromDao)),
                            new Button("Show recipe", u -> sendRecipe(u, dishFromDao)))))
                    : new Message(CommandsOutput.DISH_IS_NOT_FOUND.toStringValue()));
        }
    }

    /**
     * Попытаться с 5 попыток достать блюдо из апи.
     */
    private Dish findDishFromApi(String dishTitle, User user)
    {
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++)
        {
            Dish dish = apiService.getDishByTitle(dishTitle, user);
            if (dish != null) return dish;
        }
        return null;
    }

    /**
     * Отправить пользователю рецепт.
     */
    private void sendRecipe(User user, Dish dish)
    {
        bot.setOutput(user, new Message(dish.getRecipeOutput()));
    }

    /**
     * Отправить пользователю продукты.
     */
    private void sendProducts(User user, Dish dish)
    {
        Recipe recipe = dish.getRecipe();
        Message output = new Message(recipe.getProductsOutput());
        bot.setOutput(user, output);
    }
}

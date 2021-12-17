package commands;

import constants.CommandsOutput;
import constants.Numbers;
import model.Message;
import model.ChiefBot;
import model.Dish;
import model.Ingredient;
import model.User;
import service.APIService;
import service.IngredientService;

import java.util.ArrayList;
import java.util.List;

/**
 * Бот выводит блюда по ингредиентам.
 */
public class DishesByProducts extends Command
{
    public DishesByProducts(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS.toStringValue()));
        user.addMessageWait(this::outputDishesByProducts);
    }

    /**
     * По полученным продуктам от пользователя выводит блюда и их рецепты.
     */
    private void outputDishesByProducts(User user, Message message)
    {
        if (!IngredientService.isValidString(message.getText()))
        {
            bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS.toStringValue()));
            user.addMessageWait(this::outputDishesByProducts);
            return;
        }

        List<Ingredient> ingredients = IngredientService.getIngredients(message.getText());
        List<Dish> dishes = findDishesFromAPI(ingredients);

        if (!dishes.isEmpty())
        {
            sendDishes(user, dishes);
            return;
        }

        dishes = bot.getDishService().findDishesByIngredients(ingredients);
        if (dishes.isEmpty()) bot.setOutput(user, new Message(CommandsOutput.NOT_ENOUGH_INGREDIENTS.toStringValue()));
        else sendDishes(user, dishes);
    }

    /**
     * Попытаться с 5 попыток достать блюдо из апи.
     */
    private List<Dish> findDishesFromAPI(List<Ingredient> ingredientList)
    {
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++)
        {
            List<Dish> dishesList = APIService.getDishesByIngredients(ingredientList);
            if (dishesList != null) return dishesList;
        }

        return new ArrayList<>();
    }

    /**
     * Отправить пользователю список блюд.
     */
    private void sendDishes(User user, List<Dish> dishes)
    {
        for (Dish dish : dishes)
        {
            Message output = new Message(bot.getDishService().getStringFromDish(dish));
            output.setImageURL(dish.getImageUrl());
            bot.setOutput(user, output);
        }
    }
}

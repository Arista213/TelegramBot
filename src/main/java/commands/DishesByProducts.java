package commands;

import constants.CommandsOutput;
import constants.Numbers;
import models.*;
import services.IngredientService;

import java.util.Arrays;
import java.util.List;

/**
 * Бот выводит блюда по ингредиентам.
 */
public class DishesByProducts extends Command {
    public DishesByProducts(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS.toStringValue()));
        userService.addMessageWait(user, this::outputDishesByProducts);
    }

    /**
     * По полученным продуктам от пользователя выводит блюда и их рецепты.
     */
    private void outputDishesByProducts(User user, Message message) {
        String userText = message.getText();
        if (IngredientService.isValidString(userText)) {
            bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS.toStringValue()));
            userService.addMessageWait(user, this::outputDishesByProducts);
            return;
        }

        List<Ingredient> ingredients = IngredientService.getIngredients(userText);
        List<Dish> dishesFromAPI = findDishesFromAPI(ingredients, user);
        if (dishesFromAPI != null) {
            sendDishes(user, dishesFromAPI);
        } else {
            List<Dish> dishesFromDao = dishService.findDishesByIngredients(ingredients);
            if (dishesFromDao.isEmpty())
                bot.setOutput(user, new Message(CommandsOutput.NOT_ENOUGH_INGREDIENTS.toStringValue()));
            else
                sendDishes(user, dishesFromDao);
        }
    }

    /**
     * Попытаться с 5 попыток достать блюдо из апи.
     */
    private List<Dish> findDishesFromAPI(List<Ingredient> ingredientList, User user) {
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++) {
            List<Dish> dishesList = apiService.getDishesByIngredients(ingredientList, user);
            if (dishesList != null) return dishesList;
        }

        return null;
    }

    /**
     * Отправить пользователю список блюд.
     */
    private void sendDishes(User user, List<Dish> dishes) {
        for (Dish dish : dishes) {
            Message output = new Message(dishService.getStringFromDish(dish))
                    .setImageURL(dish.getImageUrl()).
                    setButtons(Arrays.asList(Arrays.asList(
                            new Button("Show products", u -> sendProducts(u, dish)),
                            new Button("Show recipe", u -> sendRecipe(u, dish)))));
            bot.setOutput(user, output);
        }
    }

    /**
     * Отправить пользователю рецепт.
     */
    private void sendRecipe(User user, Dish dish) {
        bot.setOutput(user, new Message(dish.getRecipeOutput()));
    }

    /**
     * Отправить пользователю продукты.
     */
    private void sendProducts(User user, Dish dish) {
        Recipe recipe = dish.getRecipe();
        Message output = new Message(recipe.getProductsOutput());
        bot.setOutput(user, output);
    }
}

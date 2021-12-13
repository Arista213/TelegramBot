package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import constants.Numbers;
import message.model.Message;
import model.*;
import service.APIService;
import service.ProductService;

import java.util.ArrayList;
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
        bot.setOutput(user, new Message(Commands.INGREDIENTS.toStringValue()));
        UserApi.addToMessageWaiter(user, this::outputDishesByProducts);
    }

    /**
     * По полученным продуктам от пользователя выводит блюда и их рецепты.
     */
    private void outputDishesByProducts(User user, Message message) {
        if (!ProductService.isValidString(message.getText())) {
            bot.setOutput(user, new Message(Commands.INGREDIENTS.toStringValue()));
            UserApi.addToMessageWaiter(user, this::outputDishesByProducts);
            return;
        }

        List<Ingredient> ingredients = ProductService.getIngredients(message.getText());
        List<Dish> dishes = findDishesFromAPI(ingredients);

        if (!dishes.isEmpty()) {
            Message output = new Message(DishApi.getStringFromDishes(dishes));
            bot.setOutput(user, output);
            return;
        }

        dishes = DishApi.findDishesByIngredients(ingredients);
        if (dishes.isEmpty()) {
            bot.setOutput(user, new Message(Commands.NOT_ENOUGH_INGREDIENTS.toStringValue()));
        } else {
            System.out.println(dishes);
            Message output = new Message(DishApi.getStringFromDishes(dishes));
            bot.setOutput(user, output);
        }
    }

    /**
     * Попытаться с 5 попыток достать блюдо из апи.
     */
    private List<Dish> findDishesFromAPI(List<Ingredient> ingredientList) {
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++) {
            List<Dish> dishesList = APIService.getDishesByIngredients(ingredientList);
            if (dishesList != null) return dishesList;
        }

        return new ArrayList<>();
    }
}

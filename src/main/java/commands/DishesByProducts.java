package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import constants.Numbers;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.Product;
import model.User;
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
        bot.setOutput(user, Commands.INGREDIENTS.toStringValue());
        UserApi.addToMessageWaiter(user, this::outputDishesByProducts);
    }

    /**
     * По полученным продуктам от пользователя выводит блюда и их рецепты.
     */
    private void outputDishesByProducts(User user, Message message) {
        if (!ProductService.isValidString(message.getText())) {
            bot.setOutput(user, Commands.INGREDIENTS.toStringValue());
            UserApi.addToMessageWaiter(user, this::outputDishesByProducts);
            return;
        }

        List<Product> products = ProductService.getProducts(message.getText());
        List<Dish> dishes = findDishesFromAPI(products);

        if (!dishes.isEmpty()) {
            String output = DishApi.getStringFromDishes(dishes);
            bot.setOutput(user, output);
            return;
        }

        dishes = DishApi.findDishesByProducts(products);
        if (dishes.isEmpty()) {
            bot.setOutput(user, Commands.NOT_ENOUGH_INGREDIENTS.toStringValue());
        } else {
            System.out.println(dishes);
            String output = DishApi.getStringFromDishes(dishes);
            bot.setOutput(user, output);
        }
    }

    /**
     * Попытаться с 5 попыток достать блюдо из апи.
     */
    private List<Dish> findDishesFromAPI(List<Product> productList) {
        for (int i = 0; i < Numbers.API_ATTEMPTS_TO_GET_REQUEST.toIntValue(); i++) {
            List<Dish> dishesList = APIService.getDishesByIngredients(productList);
            if (dishesList != null) return dishesList;
        }

        return new ArrayList<>();
    }
}

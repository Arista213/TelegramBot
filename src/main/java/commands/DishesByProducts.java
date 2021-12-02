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
        UserApi.addToMessageWaiter(user, this::findDishesByProducts);
    }

    /**
     * По полученным продуктам от пользователя найти блюдо.
     */
    private void findDishesByProducts(User user, Message message) {
        if (!isValidString(message.getText())) {
            bot.setOutput(user, Commands.INGREDIENTS.toStringValue());
            UserApi.addToMessageWaiter(user, this::findDishesByProducts);
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
     * Проверка строки на валидность.
     */
    private boolean isValidString(String products) {
        return !(products.contains("!") || products.contains("@")
                || products.contains("#") || products.contains("$")
                || products.contains("%") || products.contains("^")
                || products.contains("&") || products.contains("*")
                || products.contains("(") || products.contains(")")
                || products.contains(".") || products.contains("/")
                || products.contains("\\") || products.contains("|"));
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

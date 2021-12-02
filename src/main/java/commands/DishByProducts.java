package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.Product;
import model.User;
import service.APIService;
import service.ProductService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Бот выводит рецепты по ингредиентам.
 */
public class DishByProducts extends Command {
    public DishByProducts(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, Commands.INGREDIENTS.toStringValue());
        UserApi.addToMessageWaiter(user, this::getDishByProducts);
    }

    private void getDishByProducts(User user, Message message) {
        List<Product> productList = ProductService.getProducts(message.getText());
        Set<Product> products = new HashSet<>(productList);
        Set<Dish> dishes;
        for (int i = 0; i < 5; i++) {
            List<Dish> dishesList = APIService.getDishesByIngredients(productList);
            if (dishesList != null) {
                dishes = new HashSet<>(dishesList);
                StringBuilder result = new StringBuilder();
                result.append(Commands.CAN_BE_COOKED.toStringValue());
                dishes.forEach(e -> result.append(e).append("\n\n"));
                bot.setOutput(user, result.toString());
                return;
            }
        }
        dishes = DishApi.getAvailableForUser(products);

        if (dishes.isEmpty())
            bot.setOutput(user, Commands.NOT_ENOUGH_INGREDIENTS.toStringValue());
        else {
            StringBuilder result = new StringBuilder();
            result.append(Commands.CAN_BE_COOKED.toStringValue());
            dishes.forEach(e -> result.append(e).append("\n\n"));
            bot.setOutput(user, result.toString());
        }
    }

}

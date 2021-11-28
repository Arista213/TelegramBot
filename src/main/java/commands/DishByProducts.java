package commands;

import api.DishApi;
import api.UserApi;
import message.model.Message;
import model.ChiefBot;
import model.Dish;
import model.Product;
import model.User;
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
        bot.setOutput(user, "Введите ингредиенты, которые у вас имеются");
        UserApi.addToMessageWaiter(user, this::getDishByProducts);
    }

    private void getDishByProducts(User user, Message message) {
        List<Product> productList = ProductService.getProducts(message.getText());
        Set<Product> products = new HashSet<>(productList);
        Set<Dish> dishes = DishApi.getAvailableForUser(products);

        if (dishes.isEmpty())
            bot.setOutput(user, "Сходи в магазин(");
        else {
            StringBuilder result = new StringBuilder();
            result.append("Можно приготовить:\n");
            dishes.forEach(e -> result.append(e).append("\n\n"));
            bot.setOutput(user, result.toString());
        }
    }
}

package commands;

import api.DishApi;
import model.Bot;
import model.Dish;
import model.Product;
import service.ProductService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Бот выводит рецепты по ингредиентам.
 */
public class DishByProducts extends Command {
    public DishByProducts(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        bot.setOutput("Введите ингредиенты, которые у вас имеются");
        List<Product> productList = ProductService.getProducts(bot.requestInput());
        Set<Product> products = new HashSet<>(productList);
        Set<Dish> dishes = DishApi.getAvailableForUser(products);

        if (dishes.isEmpty())
            bot.setOutput("Сходи в магазин(");
        else {
            StringBuilder result = new StringBuilder();
            result.append("Можно приготовить:\n");
            dishes.forEach(e -> result.append(e).append("\n\n"));
            bot.setOutput(result.toString());
        }
    }
}

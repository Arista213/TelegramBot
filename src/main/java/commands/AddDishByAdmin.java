package commands;

import api.DishApi;
import model.Bot;
import model.Dish;
import model.Product;
import model.Recipe;
import service.ProductService;

import java.util.List;

/**
 * Если в режиме администратора, то мы принимаем название блюда и список инградиентов и кладем их в какой-то список.
 */
public class AddDishByAdmin extends Command {
    public AddDishByAdmin(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        if (!bot.getUser().isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы добавляете");
        String dishName = bot.requestInput();

        bot.setOutput("Введите ингредиенты, из которых будет приготовлено блюдо");
        List<Product> products = ProductService.getProducts(bot.requestInput());

        Dish dish = new Dish(dishName, new Recipe(products));
        DishApi.add(dish);
        bot.setOutput("Блюдо добавлено, надеюсь вы счастливы");
    }
}

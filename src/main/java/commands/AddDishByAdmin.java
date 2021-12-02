package commands;

import api.DishApi;
import api.UserApi;
import constants.Commands;
import message.model.Message;
import model.*;
import service.ProductService;

import java.util.List;

/**
 * Если в режиме администратора, то мы принимаем название блюда и список инградиентов и кладем их в какой-то список.
 */
public class AddDishByAdmin extends Command {
    private String dishTitle;

    public AddDishByAdmin(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        boolean isAdmin = UserApi.isAdmin(user);
        if (!isAdmin) bot.setOutput(user, Commands.NOT_ENOUGH_RIGHTS.toStringValue());
        else {
            bot.setOutput(user, Commands.DISH_TITLE_TO_ADD.toStringValue());
            UserApi.addToMessageWaiter(user, this::identifyTitle);
        }
    }

    private void identifyTitle(User user, Message message) {
        dishTitle = message.getText();
        bot.setOutput(user, Commands.INGREDIENTS_TO_ADD.toStringValue());
        UserApi.addToMessageWaiter(user, this::identifyProducts);
    }

    private void identifyProducts(User user, Message message) {
        List<Product> products = ProductService.getProducts(message.getText());
        Dish dish = new Dish(dishTitle, new Recipe(products));
        DishApi.add(dish);
        bot.setOutput(user, Commands.DISH_ADDED.toStringValue());
    }
}

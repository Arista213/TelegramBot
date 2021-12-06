package commands;

import api.DishApi;
import api.UserApi;
import constants.CommandsOutput;
import message.model.Message;
import model.*;
import service.ProductService;

import java.util.List;

/**
 * Если в режиме администратора, то мы принимаем название блюда и список инградиентов и кладем их в какой-то список.
 */
public class AddDishByAdmin extends Command {
    private String dishTitle;
    private List<Product> products;

    public AddDishByAdmin(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        boolean isAdmin = UserApi.isAdmin(user);
        if (!isAdmin) bot.setOutput(user, CommandsOutput.NOT_ENOUGH_RIGHTS.toStringValue());
        else {
            bot.setOutput(user, CommandsOutput.DISH_TITLE_TO_ADD.toStringValue());
            UserApi.addToMessageWaiter(user, this::identifyTitle);
        }
    }

    /**
     * Сохранить введенное пользователем название блюда.
     */
    private void identifyTitle(User user, Message message) {
        dishTitle = message.getText();
        bot.setOutput(user, CommandsOutput.INGREDIENTS_TO_ADD.toStringValue());
        UserApi.addToMessageWaiter(user, this::identifyProducts);
    }

    /**
     * Сохранить продукты введенные пользователем.
     */
    private void identifyProducts(User user, Message message) {
        if (!ProductService.isValidString(message.getText())) {
            bot.setOutput(user, CommandsOutput.INGREDIENTS_TO_ADD.toStringValue());
            UserApi.addToMessageWaiter(user, this::identifyProducts);
            return;
        }

        products = ProductService.getProducts(message.getText());
    }

    /**
     * Добавить блюдо в базу данных и вывести результат.
     */
    private void outputAddedDish(User user) {
        Dish dish = new Dish(dishTitle, new Recipe(products));
        DishApi.add(dish);
        bot.setOutput(user, CommandsOutput.DISH_ADDED.toStringValue());
    }
}

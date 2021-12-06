package commands;

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
    private List<Product> products;

    public AddDishByAdmin(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        boolean isAdmin = user.getMode() == Mode.Admin;
        if (!isAdmin) bot.setOutput(user, Commands.NOT_ENOUGH_RIGHTS.toStringValue());
        else {
            bot.setOutput(user, Commands.DISH_TITLE_TO_ADD.toStringValue());
            user.addMessageWait(this::identifyTitle);
        }
    }

    /**
     * Сохранить введенное пользователем название блюда.
     */
    private void identifyTitle(User user, Message message) {
        dishTitle = message.getText();
        bot.setOutput(user, Commands.INGREDIENTS_TO_ADD.toStringValue());
        user.addMessageWait(this::identifyProducts);
    }

    /**
     * Сохранить продукты введенные пользователем.
     */
    private void identifyProducts(User user, Message message) {
        if (!ProductService.isValidString(message.getText())) {
            bot.setOutput(user, Commands.INGREDIENTS_TO_ADD.toStringValue());
            user.addMessageWait(this::identifyProducts);
            return;
        }

        products = ProductService.getProducts(message.getText());
        outputAddedDish(user);
    }

    /**
     * Добавить блюдо в базу данных и вывести результат.
     */
    private void outputAddedDish(User user) {
        Dish dish = new Dish(dishTitle, new Recipe(products));
        bot.getDishDao().save(dish);
        bot.setOutput(user, Commands.DISH_ADDED.toStringValue());
    }
}

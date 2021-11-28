package commands;

import api.DishApi;
import api.UserApi;
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
        if (!isAdmin) bot.setOutput(user, "У вас недостаточно прав");
        else {
            bot.setOutput(user, "Введите название блюда, которое вы добавляете");
            UserApi.addToMessageWaiter(user, this::identifyTitle);
        }
    }

    private void identifyTitle(User user, Message message) {
        dishTitle = message.getText();
        bot.setOutput(user, "Введите ингредиенты, из которых будет приготовлено блюдо");
        UserApi.addToMessageWaiter(user, this::identifyProducts);
    }

    private void identifyProducts(User user, Message message) {
        List<Product> products = ProductService.getProducts(message.getText());
        Dish dish = new Dish(dishTitle, new Recipe(products));
        DishApi.add(dish);
        bot.setOutput(user, "Блюдо добавлено, надеюсь вы счастливы");
    }
}

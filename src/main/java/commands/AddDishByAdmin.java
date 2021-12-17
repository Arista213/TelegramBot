package commands;

import constants.CommandsOutput;
import model.Message;
import model.*;
import service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Если в режиме администратора, то мы принимаем название блюда и список инградиентов и кладем их в какой-то список.
 */
public class AddDishByAdmin extends Command
{
    public AddDishByAdmin(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        boolean isAdmin = user.getMode() == Mode.Admin;
        if (!isAdmin) bot.setOutput(user, new Message(CommandsOutput.NOT_ENOUGH_RIGHTS.toStringValue()));
        else
        {
            bot.setOutput(user, new Message(CommandsOutput.DISH_TITLE_TO_ADD.toStringValue()));
            user.addMessageWait(this::identifyTitle);
        }
    }

    /**
     * Сохранить введенное пользователем название блюда.
     */
    private void identifyTitle(User user, Message message)
    {
        String dishTitle = message.getText();
        bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS_TO_ADD.toStringValue()));
        user.addMessageWait((u, m) -> identifyProducts(u, m, dishTitle));
    }

    /**
     * Сохранить продукты введенные пользователем.
     */
    private void identifyProducts(User user, Message message, String dishTitle)
    {
        if (!IngredientService.isValidString(message.getText()))
        {
            bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS_TO_ADD.toStringValue()));
            user.addMessageWait((u, m) -> identifyProducts(u, m, dishTitle));
            return;
        }

        List<Ingredient> ingredients = IngredientService.getIngredients(message.getText());
        outputAddedDish(user, dishTitle, ingredients);
    }

    /**
     * Добавить блюдо в базу данных и вывести результат.
     */
    private void outputAddedDish(User user, String dishTitle, List<Ingredient> ingredients)
    {
        List<Product> products = ingredients.stream().map(ingredient -> new Product(ingredient, null)).collect(Collectors.toList());
        Dish dish = new Dish(dishTitle, new Recipe(products, null), null, null);
        bot.getDishDao().save(dish);
        bot.setOutput(user, new Message(CommandsOutput.DISH_ADDED.toStringValue()));
    }
}
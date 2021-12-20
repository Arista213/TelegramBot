package commands;

import constants.CommandsOutput;
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
        boolean isAdmin = userService.getMode(user) == Mode.Admin;
        if (!isAdmin) bot.setOutput(user, new Message(CommandsOutput.NOT_ENOUGH_RIGHTS.toStringValue()));
        else
        {
            bot.setOutput(user, new Message(CommandsOutput.DISH_TITLE_TO_ADD.toStringValue()));
            userService.addMessageWait(user, this::identifyTitle);
        }
    }

    /**
     * Сохранить введенное пользователем название блюда.
     */
    private void identifyTitle(User user, Message message)
    {
        String dishTitle = message.getText();
        Dish dish = new Dish(dishTitle);
        bot.setOutput(user, new Message(CommandsOutput.IMAGE_TO_ADD.toStringValue()));
        userService.addMessageWait(user, (u, m) -> identifyPicture(u, m, dish));
    }

    /**
     * Сохранить ссылку на картинку, введенную пользователем.
     */
    private void identifyPicture(User user, Message message, Dish dish)
    {
        String imageUrl = message.getText();
        dish.setImageUrl(imageUrl);
        bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS_TO_ADD.toStringValue()));
        userService.addMessageWait(user, (u, m) -> identifyProducts(u, m, dish));
    }

    /**
     * Сохранить продукты введенные пользователем.
     */
    private void identifyProducts(User user, Message message, Dish dish)
    {
        if (IngredientService.isValidString(message.getText()))
        {
            bot.setOutput(user, new Message(CommandsOutput.INGREDIENTS_TO_ADD.toStringValue()));
            userService.addMessageWait(user, (u, m) -> identifyProducts(u, m, dish));
            return;
        }

        bot.setOutput(user, new Message(CommandsOutput.SUMMARY_TO_ADD.toStringValue()));
        userService.addMessageWait(user, (u, m) -> identifySummery(user, message, dish));
    }

    /**
     * Сохранить описание блюда введенное пользователем.
     */
    private void identifySummery(User user, Message message, Dish dish)
    {
        String summary = message.getText();
        dish.setSummary(summary);
        List<Ingredient> ingredients = IngredientService.getIngredients(message.getText());
        outputAddedDish(user, dish, ingredients);
    }

    /**
     * Добавить блюдо в базу данных и вывести результат.
     */
    private void outputAddedDish(User user, Dish dish, List<Ingredient> ingredients)
    {
        List<Product> products = ingredients.stream().map(ingredient -> new Product(ingredient, null)).collect(Collectors.toList());
        dish.setRecipe(new Recipe(products, null));
        dishDao.save(dish);
        bot.setOutput(user, new Message(CommandsOutput.DISH_ADDED.toStringValue()));
    }
}
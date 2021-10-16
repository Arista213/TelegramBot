package logic.commands;

import logic.Bot;
import logic.cook.Dish;
import logic.cook.DishService;

import java.util.Arrays;
import java.util.List;

/**
 * Если есть админка, то мы принимаем название блюда и список инградиентов и кладем их в какой-то список
 */
public class AddRecipeByAdmin implements ICommand {
    public void process(Bot bot) {
        if (!bot.user.isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы добавляете");
        bot.requestInput();
        String dishName = bot.getInput();
        bot.setOutput("Введите ингредиенты, из которых будет приготовлено блюдо");
        bot.requestInput();
        List<String> ingredients = Arrays.asList(bot.getInput().split(" "));
        Dish dish = new Dish(dishName, ingredients);
        DishService.dishes.add(dish);
        bot.setOutput("Блюдо добавлено, надеюсь вы счастливы");
    }
}

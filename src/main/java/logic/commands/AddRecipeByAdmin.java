package logic.commands;

import logic.Bot;
import logic.cheif_cooker.Dish;
import logic.cheif_cooker.DishService;

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
        String dishName = bot.waitForInput();
        bot.setOutput("Введите ингредиенты, из которых будет приготовлено блюдо");
        List<String> ingredients = Arrays.asList(bot.waitForInput().split(" "));
        Dish dish = new Dish(dishName, ingredients);
        DishService.dishes.add(dish);
        bot.setOutput("Блюдо добавлено, надеюсь вы счастливы");
    }
}

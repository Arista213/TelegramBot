package logic.commands;

import logic.Bot;
import logic.cook.Dish;
import logic.cook.DishService;
import logic.cook.Recipe;

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
        String dishName = bot.requestInput();
        bot.setOutput("Введите ингредиенты, из которых будет приготовлено блюдо");
        List<String> ingredients = Arrays.asList(bot.requestInput().split(" "));
        Dish dish = new Dish(dishName, new Recipe(ingredients));
        DishService.dishes.add(dish);
        bot.setOutput("Блюдо добавлено, надеюсь вы счастливы");
    }
}

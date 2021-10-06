package brain.commands;

import brain.Bot;
import brain.cheif_cooker.Dish;
import brain.cheif_cooker.Dishes;

import java.util.Arrays;
import java.util.List;

public class AddRecipeByAdmin implements ICommand {
    public void process(Bot bot) {
        // TO DO
        // Если есть админка, то мы принимаем название блюда и список инградиентов и кладем их в какой-то список
        if (!bot.user.isAdmin()) {
            bot.setOutput("У вас недостаточно прав");
            return;
        }

        bot.setOutput("Введите название блюда, которое вы добавляете");
        String dishName = bot.waitForInput();
        bot.setOutput("Введите ингредиенты, из которых будет приготовлено блюдо");
        List<String> ingredients = Arrays.asList(bot.waitForInput().split(" "));
        Dish dish = new Dish(dishName, ingredients);
        Dishes.list.add(dish);
        bot.setOutput("Блюдо добавлено, надеюсь вы счастливы");
    }
}

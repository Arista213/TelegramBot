package brain;

import brain.cheif_cooker.Dish;
import brain.cheif_cooker.Dishes;

public class RecipeByName extends Command {

    @Override
    public void process(Bot bot) {
        bot.commandInRunning = true;
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        String dishName = null;
        while (dishName == null)
            dishName = bot.getMessage();

        String recipe = Dish.findDishByName(Dishes.list, dishName).getRecipe();
        bot.setOutput(recipe);
        bot.commandInRunning = false;
    }
}

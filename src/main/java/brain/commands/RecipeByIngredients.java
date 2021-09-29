package brain.commands;

import brain.Bot;
import brain.cheif_cooker.Dishes;

import java.util.HashSet;

/**
 * Бот выводит рецепты по ингредиентам
 */
public class RecipeByIngredients extends Command {

    @Override
    public void process(Bot bot) {
        bot.commandInRunning = true;
        bot.setOutput("Введите ингредиенты, которые у вас имеются");
        String input = bot.waitForInput();

        HashSet<String> ingredients = getIngredients(input.split(" "));
        String result = Dishes.whatCanBeCooked(ingredients);
        if (result.isBlank())
            result = "Сходи в магазин(";
        
        bot.setOutput(result);
        bot.commandInRunning = false;
    }

    private HashSet<String> getIngredients(String[] ingredientsStr) {
        return new HashSet<>() {{
            for (String ingredient : ingredientsStr)
                add(ingredient);
        }};
    }
}

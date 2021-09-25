package brain;

import brain.cheif_cooker.Dish;
import brain.cheif_cooker.Dishes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class RecipeByIngredients extends Command {

    @Override
    public void process(Bot bot) {
        bot.commandInRunning = true;
        bot.setOutput("Введите ингредиенты, которые у вас имеются");
        String message = null;
        while (message == null)
            message = bot.getMessage();

        HashSet<String> ingredients = getIngredients(message.split(" "));
        String result = Dishes.whatCanBeCooked(ingredients);
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

package logic.commands;

import logic.Bot;
import logic.cook.Dish;
import logic.cook.DishService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Бот выводит рецепты по ингредиентам
 */
public class RecipeByIngredients implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Введите ингредиенты, которые у вас имеются");
        String input = bot.requestInput();

        HashSet<String> ingredients = getIngredients(input.split(" "));
        List<Dish> dishes = DishService.getValidDishes(ingredients);
        if (dishes.isEmpty())
            bot.setOutput("Сходи в магазин(");
        else {
            StringBuilder result = new StringBuilder();
            result.append("Можно приготовить:\n");
            dishes.forEach(e -> result.append(e).append("\n\n"));
            bot.setOutput(result.toString());
        }
    }

    private HashSet<String> getIngredients(String[] ingredientsStr) {
        return new HashSet<>() {{
            this.addAll(Arrays.asList(ingredientsStr));
        }};
    }
}

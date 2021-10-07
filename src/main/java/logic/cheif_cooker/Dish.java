package logic.cheif_cooker;

import java.util.*;

public class Dish {
    public final String name;

    public Dish(String name, List<String> ingredients) {
        this.name = name;
        this.recipe = ingredients;
    }

    private final List<String> recipe;

    public boolean isIngredientsFit(HashSet<String> ingredients) {
        for (String ingredient : recipe)
            if (!ingredients.contains(ingredient))
                return false;

        return true;
    }

    public String getRecipe() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------Рецепт------\t\n");

        for (String ingredient : recipe)
            sb.append(ingredient + "\n");

        sb.append("------------------\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return name + getRecipe();
    }
}

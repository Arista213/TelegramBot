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
        return String.join(" ", recipe);
    }

    @Override
    public String toString() {
        return name + '\n' + getRecipe();
    }
}

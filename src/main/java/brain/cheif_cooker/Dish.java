package brain.cheif_cooker;

import java.util.*;

public class Dish {
    public final String name;

    public Dish(String name, Map<String, String> ingredients) {
        this.name = name;
        this.recipe = ingredients;
    }

    private final Map<String, String> recipe;

    public boolean isIngredientsFit(HashSet<String> ingredients) {
        for (String ingredient : recipe.keySet())
            if (!ingredients.contains(ingredient))
                return false;

        return true;
    }

    public static Dish findDishByName(List<Dish> dishes, String name) {
        for (Dish dish : dishes) {
            if (dish.name.equalsIgnoreCase(name))
                return dish;
        }

        return null;
    }

    public String getRecipe() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------Рецепт------\t\n");

        for (String ingredient : recipe.keySet()) {
            sb.append(ingredient + "\t-\t" + recipe.get(ingredient) + "\n");
        }

        sb.append("------------------\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return name + getRecipe();
    }
}

package logic.cook;

import java.util.List;
import java.util.Set;

public class Recipe {
    public Recipe(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    private final List<String> ingredients;

    public boolean isIngredientsFit(Set<String> ingredients) {
        for (String ingredient : this.ingredients)
            if (!ingredients.contains(ingredient))
                return false;

        return true;
    }

    public boolean isEqual(Recipe otherRecipe) {
        if (ingredients.size() != otherRecipe.ingredients.size())
            return false;

        for (String ingredient : ingredients)
            if (!otherRecipe.ingredients.contains(ingredient))
                return false;

        return true;
    }

    @Override
    public String toString() {
        return String.join(" ", ingredients);
    }
}

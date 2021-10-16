package logic.cook;

import java.util.*;

public class Dish {
    public final String title;
    public final Boolean isExist;
    private final Recipe recipe;

    public Dish() {
        title = null;
        recipe = null;
        isExist = false;
    }

    public Dish(String name, List<String> ingredients) {
        this.title = name;
        this.recipe = new Recipe(ingredients);
        isExist = true;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return title + "\n" + recipe;
    }
}

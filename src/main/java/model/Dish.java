package model;

import java.util.Objects;

/**
 * Сущность блюда.
 */
public class Dish {
    public final String title;
    private final Recipe recipe;

    public Dish(String name, Recipe recipe) {
        this.title = name;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(title, dish.title) && Objects.equals(recipe, dish.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, recipe);
    }

    @Override
    public String toString() {
        return title + "\n" + recipe;
    }
}

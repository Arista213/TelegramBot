package model;

import java.util.Objects;

/**
 * Сущность блюда.
 */
public class Dish {
    public String title;
    public boolean isExist;
    private Recipe recipe;

    public Dish() {
    }

    public Dish(String name, Recipe recipe) {
        this.title = name;
        this.recipe = recipe;
        isExist = true;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(title, dish.title) && Objects.equals(isExist, dish.isExist) && Objects.equals(recipe, dish.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isExist, recipe);
    }

    @Override
    public String toString() {
        return title + "\n" + recipe;
    }
}

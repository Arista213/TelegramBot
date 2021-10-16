package logic.cook;

public class Dish {
    public final String title;
    public final Boolean isExist;
    private final Recipe recipe;

    public Dish() {
        title = null;
        recipe = null;
        isExist = false;
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
    public String toString() {
        return title + "\n" + recipe;
    }
}

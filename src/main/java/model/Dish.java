package model;

import java.util.Objects;

/**
 * Сущность блюда.
 */
public class Dish
{
    private final String title;
    private final Recipe recipe;
    private final String imageUrl;
    private final String summary;

    public Dish(String name, Recipe recipe, String imageUrl, String summery)
    {
        this.title = name;
        this.recipe = recipe;
        this.imageUrl = imageUrl;
        this.summary = summery;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getSummary()
    {
        return summary;
    }

    public String getTitle()
    {
        return title;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(title, dish.title) && Objects.equals(recipe, dish.recipe);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(title, recipe);
    }

    @Override
    public String toString()
    {
        return "Dish{" +
                "title='" + title + '\'' +
                ", recipe=" + recipe +
                '}';
    }
}

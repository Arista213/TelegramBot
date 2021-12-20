package model;

import java.util.HashSet;
import java.util.Objects;

/**
 * Шаг готовки, включающий в себя описание и ингредиенты, используемые на данном шаге.
 */
public class CookPhaseStep
{
    private final String description;
    private final HashSet<Ingredient> ingredients;

    public CookPhaseStep(String description, HashSet<Ingredient> ingredients)
    {
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getDescription()
    {
        return description;
    }

    public HashSet<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public String getIngredientsOutput()
    {
        return String.join(", ", getIngredientsTitles());
    }

    private HashSet<String> getIngredientsTitles()
    {
        HashSet<String> ingredientsTitles = new HashSet<>();
        ingredients.forEach(i -> ingredientsTitles.add(i.getTitle()));
        return ingredientsTitles;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookPhaseStep cookPhaseStep = (CookPhaseStep) o;
        return Objects.equals(description, cookPhaseStep.description)
                && Objects.equals(ingredients, cookPhaseStep.ingredients);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(description, ingredients);
    }

    @Override
    public String toString()
    {
        return "CookPhaseStep{" +
                "description='" + description + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}

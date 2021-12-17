package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Рецепт, из которого состоит блюдо.
 */
public class Recipe
{
    private final List<Product> products;
    private final List<CookPhase> phases;
    private final HashSet<Ingredient> ingredients;

    public Recipe(List<Product> products, List<CookPhase> phases)
    {
        this.products = products;
        this.phases = phases;
        this.ingredients = new HashSet<>();
        fillIngredients();
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public List<CookPhase> getPhases()
    {
        return phases;
    }

    public HashSet<Ingredient> getIngredients()
    {
        return ingredients;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(products, recipe.products);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(products);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < products.size(); i++)
        {
            if (i != products.size() - 1) result.append(products.get(i).toString()).append(",");
            else result.append(products.get(i).toString());
        }
        return result.toString();
    }

    private void fillIngredients()
    {
        if (products.size() == 0)
            return;
        for (Product product : products)
        {
            ingredients.add(product.getIngredient());
        }
    }
}

package model;

import java.util.Objects;

/**
 * Сущность для продуктов, используемых в рецептах.
 */
public class Product
{
    private final Ingredient ingredient;
    private final String outputAmount;

    public Product(Ingredient ingredient, String amount)
    {
        this.ingredient = ingredient;
        this.outputAmount = amount;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public String getOutputAmount()
    {
        return outputAmount == null ? ingredient.getTitle() : outputAmount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(ingredient, product.ingredient) && Objects.equals(outputAmount, product.outputAmount);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ingredient, outputAmount);
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "ingredient=" + ingredient +
                ", outputAmount='" + outputAmount + '\'' +
                '}';
    }
}

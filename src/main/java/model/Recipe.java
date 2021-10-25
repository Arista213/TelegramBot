package model;

import java.util.List;
import java.util.Objects;

/**
 * Рецепт, из которого состоит блюдо.
 */
public class Recipe {
    private final List<Product> products;

    public Recipe(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(products, recipe.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            if (i != products.size() - 1) result.append(products.get(i).toString()).append(" ");
            else result.append(products.get(i).toString());
        }
        return result.toString();
    }
}

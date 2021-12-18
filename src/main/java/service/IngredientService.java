package service;

import model.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с продуктами.
 */
public abstract class IngredientService
{
    /**
     * @param ingredientStr введённые пользователем продукты в виде строки.
     * @return лист продуктов.
     */
    public static List<Ingredient> getIngredients(String ingredientStr)
    {
        String[] products = ingredientStr.split(",");
        return Arrays.stream(products).map(Ingredient::new).collect(Collectors.toList());
    }

    /**
     * Проверка строки на валидность.
     */
    public static boolean isValidString(String ingredients)
    {
        return ingredients.contains("!") || ingredients.contains("@")
                || ingredients.contains("#") || ingredients.contains("$")
                || ingredients.contains("%") || ingredients.contains("^")
                || ingredients.contains("&") || ingredients.contains("*")
                || ingredients.contains("(") || ingredients.contains(")")
                || ingredients.contains(".") || ingredients.contains("/")
                || ingredients.contains("\\") || ingredients.contains("|");
    }
}

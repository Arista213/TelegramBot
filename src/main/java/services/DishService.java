package services;

import dao.DishDao;
import models.Dish;
import models.Ingredient;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс для работы с блюдами.
 */
public class DishService {
    private final DishDao dishDao;

    public DishService(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    /**
     * Найти блюдо по продуктам.
     */
    public List<Dish> findDishesByIngredients(Collection<Ingredient> ingredients) {
        return dishDao.getAll().stream().filter(dish -> isProductsFit(dish, ingredients)).collect(Collectors.toList());
    }

    /**
     * Найти блюдо по его названию.
     */
    public Dish findDishByTitle(String title) {

        return dishDao.getAll().stream().filter(dish -> Objects.equals(dish.getTitle().toLowerCase(), title.toLowerCase())).findFirst().orElse(null);
    }

    /**
     * Получить строку из блюда.
     */
    public String getStringFromDish(Dish dish) {
        return dish.getTitle() + "\n\n" + dish.getSummary();
    }

    /**
     * Проверка подходит ли список продуктов рецепту блюда.
     */
    private boolean isProductsFit(Dish dish, Collection<Ingredient> ingredients) {
        return ingredients.containsAll(dish.getRecipe().getIngredients());
    }
}

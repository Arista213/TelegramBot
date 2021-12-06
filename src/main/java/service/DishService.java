package service;

import constants.Commands;
import dao.DishDao;
import model.Dish;
import model.Product;

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
    public List<Dish> findDishesByProducts(List<Product> products) {
        return dishDao.getAll().stream().filter(dish -> isProductsFit(dish, products)).collect(Collectors.toList());
    }

    /**
     * Найти блюдо по его названию.
     */
    public Dish findDishByTitle(String title) {

        return dishDao.getAll().stream().filter(dish -> Objects.equals(dish.title.toLowerCase(), title.toLowerCase())).findFirst().orElse(null);
    }

    /**
     * Получить вывод из блюд.
     */
    public String getStringFromDishes(List<Dish> dishes) {
        StringBuilder result = new StringBuilder();
        result.append(Commands.CAN_BE_COOKED.toStringValue());
        dishes.forEach(e -> result.append(e).append("\n\n"));
        return result.toString();
    }

    /**
     * Проверка подходит ли список продуктов рецепту блюда.
     */
    private boolean isProductsFit(Dish dish, List<Product> products) {
        return products.containsAll(dish.getRecipe().getProducts());
    }
}

package api;

import constants.Commands;
import model.Dish;
import model.Ingredient;
import model.Product;
import model.Recipe;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для работы с блюдами.
 */
public abstract class DishApi {
    /**
     * Здесь хранятся все блюда, с которыми можно взаимодействовать.
     */
    private static List<Dish> dishes;

    /**
     * @param ingredients имеющиеся у пользователя.
     * @return блюда, которые могут быть приготовлены.
     */
    public static List<Dish> findDishesByIngredients(List<Ingredient> ingredients) {
        return dishes.stream().filter(dish -> isProductsFit(dish, ingredients)).collect(Collectors.toList());
    }

    /**
     * Найти блюдо по его названию.
     * @param title название блюда.
     * @return блюдо.
     */
    public static Dish findDishByTitle(String title) {
        return dishes.stream().filter(dish -> Objects.equals(dish.getTitle().toLowerCase(), title.toLowerCase())).findFirst().orElse(null);
    }

    /**
     * Удалить блюдо из базы блюд.
     */
    public static void remove(String dishTitle) {
        dishes.remove(dishes.stream().filter(dish -> dish.getTitle().equalsIgnoreCase(dishTitle)).findFirst().orElse(null));
    }

    /**
     * @param dish добавить блюдо в базу данных.
     */
    public static void add(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Список всех блюд.
     */
    public static List<Dish> getAll() {
        return dishes;
    }

    /**
     * Получить вывод из блюд.
     */
    public static String getStringFromDishes(List<Dish> dishes) {
        StringBuilder result = new StringBuilder();
        dishes.forEach(e -> result.append(e.getTitle()).append("\n\n").append(e.getSummary()));
        return result.toString();
    }

    /**
     * Инициализация списка блюд.
     */
    public static void initiate() {
        dishes = new ArrayList<>() {{
            add(new Dish("Яичница", new Recipe(List.of(
                    new Product(new Ingredient("яйца"), "2 яица")), null),
                    null, null));
            add(new Dish("Блины", new Recipe(Arrays.asList(
                    new Product(new Ingredient("яйца"), "3 яйца"),
                    new Product(new Ingredient("мука"), "1 стакан муки"),
                    new Product(new Ingredient("молоко"), "2 стакана молока")), null),
                    null, null));
        }};
    }

    public static void initiate(List<Dish> dishes) {
        DishApi.dishes = dishes;
    }

    /**
     * Проверка подходит ли список продуктов рецепту блюда.
     */
    private static boolean isProductsFit(Dish dish, List<Ingredient> ingredients) {
        return ingredients.containsAll(dish.getRecipe().getIngredients());
    }
}

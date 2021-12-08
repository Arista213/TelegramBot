package api;

import constants.Commands;
import model.Dish;
import model.Product;
import model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
     * @param products имеющиеся у пользователя.
     * @return блюда, которые могут быть приготовлены.
     */
    public static List<Dish> findDishesByProducts(List<Product> products) {
        return dishes.stream().filter(dish -> isProductsFit(dish, products)).collect(Collectors.toList());
    }

    /**
     * Найти блюдо по его названию.
     *
     * @param title название блюда.
     * @return блюдо.
     */
    public static Dish findDishByTitle(String title) {
        return dishes.stream().filter(dish -> Objects.equals(dish.title.toLowerCase(), title.toLowerCase())).findFirst().orElse(null);
    }

    /**
     * Удалить блюдо из базы блюд.
     */
    public static void remove(String dishTitle) {
        dishes.remove(dishes.stream().filter(dish -> dish.title.equalsIgnoreCase(dishTitle)).findFirst().orElse(null));
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
        result.append(Commands.CAN_BE_COOKED.toStringValue());
        dishes.forEach(e -> result.append(e).append("\n\n"));
        return result.toString();
    }

    /**
     * Инициализация списка блюд.
     */
    public static void initiate() {
        dishes = new ArrayList<>() {{
            add(new Dish("Яичница", new Recipe(List.of(new Product("яйца")))));
            add(new Dish("Блины", new Recipe(Arrays.asList(
                    new Product("яйца"),
                    new Product("мука"),
                    new Product("молоко")
            ))));
        }};
    }

    public static void initiate(List<Dish> dishes) {
        DishApi.dishes = dishes;
    }

    /**
     * Проверка подходит ли список продуктов рецепту блюда.
     */
    private static boolean isProductsFit(Dish dish, List<Product> products) {
        return products.containsAll(dish.getRecipe().getProducts());
    }
}

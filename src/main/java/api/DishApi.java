package api;

import model.Dish;
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
     * @param products имеющиеся у пользователя.
     * @return блюда, которые могут быть приготовлены.
     */
    public static Set<Dish> getAvailableForUser(Set<Product> products) {
        return dishes.stream().filter(dish -> isProductsFit(dish, products)).collect(Collectors.toSet());
    }

    /**
     * Найти блюдо по его названию.
     *
     * @param title название блюда.
     * @return блюдо.
     */
    public static Dish findDishByTitle(String title) {
        return dishes.stream().filter(dish -> Objects.equals(dish.title.toLowerCase(), title.toLowerCase())).findFirst().orElse(new Dish());
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
    private static boolean isProductsFit(Dish dish, Set<Product> products) {
        return dish.getRecipe().getProducts().containsAll(products);
    }
}

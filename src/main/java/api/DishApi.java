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
        return dishes.stream().filter(x -> isProductsFit(x, products)).collect(Collectors.toSet());
    }

    /**
     * Найти блюдо по его названию.
     *
     * @param title название блюда.
     * @return блюдо.
     */
    public static Dish findDishByTitle(String title) {
        return dishes.stream().filter(x -> Objects.equals(x.title, title)).findFirst().orElse(new Dish());
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
    public static void initiateDefault() {
        dishes = new ArrayList<>() {{
            add(new Dish("Яичница", new Recipe(List.of(new Product("яйца")))));
            add(new Dish("Блины", new Recipe(Arrays.asList(
                    new Product("яйца"),
                    new Product("мука"),
                    new Product("молоко")
            ))));
        }};
    }

    /**
     * Проверка подходит ли список продуктов рецепту блюда.
     */
    private static boolean isProductsFit(Dish dish, Set<Product> products) {
        return products.containsAll(dish.getRecipe().getProducts());
    }

}

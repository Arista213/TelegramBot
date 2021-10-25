package api;

import model.Dish;
import model.Product;
import model.Recipe;

import java.util.*;

/**
 * Класс для работы с блюдами.
 */
public class DishApi {
    /**
     * Здесь хранятся все блюда, с которыми можно взаимодействовать.
     */
    private static List<Dish> dishes;

    /**
     * @param products имеющиеся у пользователя.
     * @return блюда, которые могут быть приготовлены.
     */
    public static Set<Dish> getValidDishes(Set<Product> products) {
        HashSet<Dish> suitableDishes = new HashSet<>();
        for (Dish dish : dishes)
            if (isProductsFit(dish, products))
                suitableDishes.add(dish);

        return suitableDishes;
    }

    /**
     * Найти блюдо по его названию.
     *
     * @param title название блюда.
     * @return блюдо.
     */
    public static Dish findDishByTitle(String title) {
        for (Dish dish : dishes) {
            if (dish.title.equalsIgnoreCase(title))
                return dish;
        }

        return new Dish();
    }

    /**
     * Удалить блюдо из "базы блюд".
     */
    public static void remove(String dishTitle) {
        for (int i = 0; i < dishes.size(); i++) {
            Dish currentDish = dishes.get(i);
            if (currentDish.title.equals(dishTitle)) {
                dishes.remove(i);
                break;
            }
        }
    }

    /**
     * @param dish добавить блюдо в "базу данных".
     */
    public static void add(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Проверка подходит ли список продуктов рецепту блюда.
     */
    public static boolean isProductsFit(Dish dish, Set<Product> products) {
        for (Product product : dish.getRecipe().getProducts())
            if (!products.contains(product))
                return false;

        return true;
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

}

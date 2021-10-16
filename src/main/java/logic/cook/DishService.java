package logic.cook;

import java.util.*;

/**
 * Сервис для работы с блюдами.
 */
public class DishService {
    /**
     * Здесь хранятся все блюда, с которыми можно взаимодействовать.
     */
    public static List<Dish> dishes = new ArrayList<>() {{
        add(new Dish("Яичница", new Recipe(List.of("яйца"))));
        add(new Dish("Блины", new Recipe(Arrays.asList("яйца", "мука", "молоко"))));
    }};

    /**
     * @param ingredients имеющиеся у пользователя.
     * @return блюда, которые могут быть приготовлены.
     */
    public static List<Dish> getValidDishes(Set<String> ingredients) {
        List<Dish> suitableDishes = new ArrayList<>();
        for (Dish dish : dishes)
            if (dish.getRecipe().isIngredientsFit(ingredients))
                suitableDishes.add(dish);

        return suitableDishes;
    }

    public static Dish getDishByTitle(String title) {
        for (Dish dish : dishes) {
            if (dish.title.equalsIgnoreCase(title))
                return dish;
        }

        return new Dish();
    }

    public static void removeDish(String dishTitle) {
        for (int i = 0; i < dishes.size(); i++) {
            Dish currentDish = dishes.get(i);
            if (currentDish.title.equals(dishTitle)) {
                dishes.remove(i);
                break;
            }
        }
    }

    public static String getStringFromDishes(List<Dish> dishes) {
        StringBuilder result = new StringBuilder();
        dishes.forEach(e -> result.append(e).append("\n\n"));
        return result.toString();
    }
}

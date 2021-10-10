package logic.cheif_cooker;

import java.util.*;

/**
 * Сервис для работы с блюдами
 */
public class DishService {
    /**
     * Здесь хранятся все блюда, с которыми можно взаимодействовать
     */
    public static List<Dish> dishes = new ArrayList<>() {{
        add(new Dish("Яичница", Arrays.asList("яйца")));
        add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }};

    public static String whatCanBeCooked(HashSet<String> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishes)
            if (dish.isIngredientsFit(ingredients))
                sb.append("Можно приготовить: " + dish + "\n");

        return sb.toString();
    }

    public static Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.name.equalsIgnoreCase(name))
                return dish;
        }

    return new Dish("", Arrays.asList(""));
    }

    public static void removeDish(String dishName) {
        for (int i = 0; i < dishes.size(); i++) {
            Dish currentDish = dishes.get(i);
            if (currentDish.name.equals(dishName)) {
                dishes.remove(i);
                break;
            }
        }
    }
}

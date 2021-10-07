package logic.cheif_cooker;

import java.util.*;

public class Dishes {
    public static List<Dish> dishesList = new ArrayList<>() {{
        add(new Dish("Яичница", Arrays.asList("яйца")));
        add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }};

    public static String whatCanBeCooked(HashSet<String> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishesList)
            if (dish.isIngredientsFit(ingredients))
                sb.append("Можно приготовить: " + dish + "\n");

        return sb.toString();
    }

    public static Dish getDishByName(String name) {
        for (Dish dish : dishesList) {
            if (dish.name.equalsIgnoreCase(name))
                return dish;
        }

        return null;
    }

    public static void removeDish(String dishName) {
        for (int i = 0; i < dishesList.size(); i++) {
            Dish currentDish = dishesList.get(i);
            if (currentDish.name.equals(dishName)) {
                dishesList.remove(i);
                break;
            }
        }
    }
}

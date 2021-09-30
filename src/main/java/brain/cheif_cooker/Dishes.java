package brain.cheif_cooker;

import java.util.*;

public class Dishes {
    public static List<Dish> list = new ArrayList<>() {{
        add(new Dish("Яишница", Arrays.asList("яйца")));
        add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }};

    public static String whatCanBeCooked(HashSet<String> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : list) {
            if (dish.isIngredientsFit(ingredients)) {
                sb.append("Можно приготовить: " + dish + "\n");
            }
        }

        return sb.toString();
    }

    public static void removeDish(String dishName) {
        for (int i = 0; i < list.size(); i++) {
            Dish currentDish = list.get(i);
            if (currentDish.name.equals(dishName)) {
                list.remove(i);
                break;
            }
        }
    }
}

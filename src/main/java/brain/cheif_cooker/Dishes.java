package brain.cheif_cooker;

import java.util.*;

public class Dishes {
    public static List<Dish> list = new ArrayList<>() {{
        add(new Dish("Яишница", new HashMap<>() {{
            put("яйца", "2 шт");
        }}));

        add(new Dish("Блины", new HashMap<>() {{
            put("яйца", "3 шт");
            put("мука", "300г");
            put("молоко", "500мл");
        }}));
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
}

import Functionality.Dish;

import java.util.*;

import static Functionality.JSONWorker.saveDishesToJSON;
import static Functionality.JSONWorker.loadDishesFromJSON;

public class TestClass {
    public static void main(String[] args) {
        HashSet<String> ingredients = new HashSet<>() {{
            add("яйца");
            add("тесто");
            add("молоко");
        }};

        Dish friedEggs = new Dish("Яишница", new HashMap<>() {{
            put("яйца", "2 шт");
        }});

        Dish pancakes = new Dish("Блины", new HashMap<>() {{
            put("яйца", "3 шт");
            put("тесто", "300г");
            put("молоко", "500мл");
        }});

        List<Dish> dishes = Arrays.asList(friedEggs, pancakes);

        saveDishesToJSON(dishes, "test.json");

        List<Dish> dishList = loadDishesFromJSON("test.json");
        System.out.println(dishList);
    }

    private static String whatCanBeCooked(HashSet<String> ingredients, List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : dishes) {
            if (dish.isIngredientsFit(ingredients)) {
                sb.append("Можно приготовить: " + dish + "\n");
            }
        }
        return sb.toString();
    }


}

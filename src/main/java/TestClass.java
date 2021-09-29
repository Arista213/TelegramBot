import brain.cheif_cooker.Dish;
import brain.cheif_cooker.JSONWorker;

import java.util.*;

public class TestClass {
    public static void main(String[] args) {
        HashSet<String> ingredients = new HashSet<>() {{
            add("яйца");
            add("тесто");
            add("молоко");
        }};

        Dish friedEggs = new Dish("Яишница", Arrays.asList("яйца"));
        Dish pancakes = new Dish("Блины", Arrays.asList("яйца", "тесто", "молоко"));

        List<Dish> dishes = new ArrayList<>() {{
            add(friedEggs);
            add(pancakes);
        }};

        JSONWorker.saveDishesToJSON(dishes, "test.json");
    }
}

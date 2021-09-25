import brain.Functionality.Dish;

import java.util.*;

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

        List<Dish> dishes = new ArrayList<>() {{
            add(friedEggs);
            add(pancakes);
        }};

        for (Dish dish : dishes) {
            if (dish.isIngredientsFit(ingredients)) {
                System.out.println("Можно приготовить: " + dish + "\n");
            }
        }

        System.out.println("Поиск блюда по названию");
        Dish d = Dish.findDishByName(dishes, "яишница");
        System.out.println(d);
    }
}

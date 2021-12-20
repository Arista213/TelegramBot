package dao.impl;

import dao.DishDao;
import model.Dish;
import model.Ingredient;
import model.Product;
import model.Recipe;

import java.util.*;

public class SimpleDishDao extends DishDao
{
    private Map<String, Dish> dishes = new HashMap<>();

    public SimpleDishDao()
    {
        initiate();
    }

    @Override
    public Dish get(String title)
    {
        return dishes.get(title.toLowerCase());
    }

    @Override
    public List<Dish> getAll()
    {
        return new ArrayList<>(dishes.values());
    }

    @Override
    public void save(Dish dish)
    {
        dishes.put(dish.getTitle().toLowerCase(), dish);
    }

    @Override
    public void update(String title, Dish dish)
    {
        dishes.put(title.toLowerCase(), dish);
    }

    @Override
    public void delete(Dish dish)
    {
        dishes.remove(dish.getTitle().toLowerCase());
    }

    /**
     * Инициализация списка блюд.
     */
    private void initiate()
    {
        dishes = new HashMap<>()
        {
            {
                put("Яичница", new Dish("Яичница",
                        new Recipe(List.of(new Product(new Ingredient("яйца"), "2 яица")),
                                null), null, null));

                put("Блины", new Dish("Блины", new Recipe(Arrays.asList(
                        new Product(new Ingredient("яйца"), "3 яйца"),
                        new Product(new Ingredient("мука"), "1 стакан муки"),
                        new Product(new Ingredient("молоко"), "2 стакана молока")),
                        null), null, null));
            }
        };
    }
}

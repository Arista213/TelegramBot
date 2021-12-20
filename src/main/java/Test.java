import dao.DishDao;
import dao.impl.DishDaoImpl;
import model.Dish;
import model.Ingredient;
import model.Product;
import model.Recipe;

import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        DishDao dishDao = new DishDaoImpl();
        Dish dish = new Dish("ADS", new Recipe(List.of(new Product(new Ingredient("1"), "1")), null), null, null);
        dishDao.save(dish);
//        JSONDish jsonDish = new JSONDish(dish);
//        System.out.println(jsonDish.getTitle());
//        System.out.println(jsonDish.getJsonString());
    }
}

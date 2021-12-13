import model.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import service.JSONService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты на сервис для работы с json.
 */
public class JSONServiceTests
{
    /**
     * Тест проверяет JSONService на сохрание блюд и их загрузку.
     */
    @Test
    public void jsonServiceSaveAndLoadTest()
    {
        String path = "target" + System.getProperty("file.separator") + "JsonSerialisationTest.txt";
        Dish friedEggs = new Dish("Яишница", new Recipe(List.of(
                new Product(new Ingredient("яица"), "2 яица")),
                null), null, null);
        Dish pancakes = new Dish("Блины", new Recipe(Arrays.asList(
                new Product(new Ingredient("яйца"), "3 яйца"),
                new Product(new Ingredient("мука"), "1 стакан муки"),
                new Product(new Ingredient("молоко"), "2 стакана молока")),
                null), null, null);
        List<Dish> expectedDishes = Arrays.asList(pancakes, friedEggs);
        JSONService.saveDishes(expectedDishes, path);
        List<Dish> actualDishes = JSONService.loadDishes(path);

        assertEquals(expectedDishes, actualDishes);
    }

    /**
     * Тест проверяет JSONService на поиск определенного количества блюд из JSON.
     */
    @Test
    public void getDishesTest() throws IOException
    {
        String separator = System.getProperty("file.separator");
        String path = "src" + separator + "test" + separator + "testFiles" + separator + "JsonGetDishesTest.txt";
        Dish soup = new Dish("African Chicken Peanut Stew", new Recipe(List.of(
                new Product(new Ingredient("bell peppers"), "Bell Peppers for garnishing"),
                new Product(new Ingredient("chicken"), "1.5 cups of Chopped Chicken"),
                new Product(new Ingredient("cooking oil"), "2.5 Cooking spoons of oil"),
                new Product(new Ingredient("curry paste"), "1 teaspoon of Curry"),
                new Product(new Ingredient("garlic cloves"), "2 garlic cloves"),
                new Product(new Ingredient("ginger"), "Small piece of Chopped ginger"),
                new Product(new Ingredient("groundnut"), "1 cup of groundnut (Blended) or 1 Cooking spoon of peanut Butter"),
                new Product(new Ingredient("onions"), "2 handfuls of Chopped onions"),
                new Product(new Ingredient("bell pepper"), "Pepper"),
                new Product(new Ingredient("salt"), "Salt"),
                new Product(new Ingredient("seasoning"), "Seasoning"),
                new Product(new Ingredient("sweet potato"), "1/2 small sweet potato (Chopped)"),
                new Product(new Ingredient("thyme"), "Pinch of thyme"),
                new Product(new Ingredient("tomato"), "1 Chopped small tomato"),
                new Product(new Ingredient("tomato"), "1.5 Cooking spoons of Blended tomato")),
                getSoupCookPhases()), "https://spoonacular.com/recipeImages/716268-312x231.jpg",
                getCookSummary());

        List<Dish> expectedDishes = List.of(soup);
        File file = new File(path);
        String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        List<Dish> actualDishes = JSONService.GetDishes(json, 1);
        assertEquals(expectedDishes, actualDishes);
    }

    private String getCookSummary()
    {
        return "Need a gluten free and dairy free main course? " +
                "African Chicken Peanut Stew could be a tremendous recipe to try." +
                " This recipe makes 1 servings with 1377 calories, 75g of protein, and 102g of fat each." +
                " For $3.87 per serving, this recipe covers 62% of your daily requirements " +
                "of vitamins and minerals. It can be enjoyed any time, but it is especially good for Autumn. " +
                "From preparation to the plate, this recipe takes approximately 45 minutes. " +
                "124 people have tried and liked this recipe. Head to the store and pick up " +
                "bell peppers, tomato, pepper, and a few other things to make it today. " +
                "To use up the tomato you could follow this main course with the Pink Peony Popcorn Balls as a dessert. " +
                "All things considered, we decided this recipe deserves a spoonacular score of 99%. " +
                "This score is super. Try African Chicken Peanut Stew, West African Peanut-Chicken Stew," +
                " and One-Pot African Peanut Stew for similar recipes.";
    }

    private List<CookPhase> getSoupCookPhases()
    {
        return List.of(new CookPhase("", List.of(
                new CookPhaseStep("Season and Boil the Chicken for 10 minutes with salt, " +
                        "pepper, seasoning, a handful of onions.Once the chicken is ready, in the same stock, " +
                        "Boil the chopped sweet potatoes till its almost cooked. Save the stock in a separate " +
                        "Bowl and the chicken and potatoes in a separate Bowl as well.In a pot, heat up one " +
                        "cooking spoon of oil and fry the chicken till it Browns. Take it out and heat up the " +
                        "other 1.5 cooking spoons of oil and fry the onions, tomatoes Both chopped " +
                        "and Blended, ginger and garlic.",
                        new HashSet<>(Set.of(
                                new Ingredient("sweet potato"), new Ingredient("seasoning"),
                                new Ingredient("potato"), new Ingredient("tomato"),
                                new Ingredient("whole chicken"), new Ingredient("garlic"),
                                new Ingredient("ginger"), new Ingredient("onion"),
                                new Ingredient("pepper"), new Ingredient("stock"),
                                new Ingredient("salt"), new Ingredient("cooking oil")
                        ))),
                new CookPhaseStep("Add your seasoning, curry, thyme, parsley, salt and pepper to the pot.",
                        new HashSet<>(Set.of(
                                new Ingredient("salt and pepper"), new Ingredient("seasoning"),
                                new Ingredient("parsley"), new Ingredient("curry powder"),
                                new Ingredient("thyme")
                        ))),
                new CookPhaseStep("Pour in your stock, chicken and potatoes to cook further." +
                        "Stir in your peanut Butter and allow to cook for 10 minutes on low heat." +
                        "If your sauce gets too thick, add a little water to it.",
                        new HashSet<>(Set.of(
                                new Ingredient("peanut butter"), new Ingredient("potato"),
                                new Ingredient("whole chicken"), new Ingredient("sauce"),
                                new Ingredient("stock"), new Ingredient("water")
                        ))),
                new CookPhaseStep("Serve with white rice or more sweet potatoes." +
                        "You could also garnish the dish with Bell peppers. ",
                        new HashSet<>(Set.of(
                                new Ingredient("sweet potato"), new Ingredient("bell pepper"),
                                new Ingredient("white rice")
                        )))
        )));
    }
}

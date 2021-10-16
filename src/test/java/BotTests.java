import logic.User;
import logic.cook.Dish;
import logic.cook.DishService;
import logic.Bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class BotTests {
    Bot bot;
    TestCommunicateService service;

    @BeforeEach
    void setUp() {
        service = new TestCommunicateService();
        bot = new Bot(service, new User());
        discardDishes();
    }

    private void discardDishes() {
        DishService.dishes = new ArrayList<>();
        DishService.dishes.add(new Dish("Яишница", List.of("яйца")));
        DishService.dishes.add(new Dish("Блины", Arrays.asList("яйца", "мука", "молоко")));
    }

    @Test
    void botStartTest() {
        service.setInstruction(List.of("/start"));
        service.runInstruction(bot);
        
        String answer = service.getOutput();
        String expected = "Шеф-повар здесь!\nНапиши мне /help и я расскажу, что умею\n";
        assertEquals(expected, answer);
    }

    @Test
    void botUnknownCommandTest() {
        service.setInstruction(List.of("Неизвестная комманда"));
        service.runInstruction(bot);
        
        String answer = service.getOutput();
        String expected = "Неизвестная комманда";
        assertEquals(expected, answer);
    }


    @Test
    void botRecipeByNameTest() {
        service.setInstruction(Arrays.asList(
                "/recipe_name",
                "яишница"
        ));
        service.runInstruction(bot);
        String answer;
        String expected;

        answer = service.getOutput();
        expected = "Введите название блюда, которое вы хотите приготовить";
        assertEquals(expected, answer);

        answer = service.getOutput();
        expected = "яйца";
        assertEquals(expected, answer);
    }

    @Test
    void botRecipeByIngredientsTest() {
        service.setInstruction(Arrays.asList(
                "/recipe_ingredients",
                "яйца молоко мука"
        ));
        service.runInstruction(bot);
        String answer;
        String expected;

        answer = service.getOutput();
        expected = "Введите ингредиенты, которые у вас имеются";
        assertEquals(expected, answer);

        answer = service.getOutput();
        expected = "Можно приготовить:\n" +
                "Яишница\n" +
                "яйца\n\n" +
                "Блины\n" +
                "яйца мука молоко\n\n";
        assertEquals(expected, answer);
    }

    @Test
    void adminTest() {
        service.setInstruction(Arrays.asList(
                "/admin_on",
                "/admin_off"
        ));
        service.runInstruction(bot);
        String answer;
        String expected;

        answer = service.getOutput();
        expected = "Теперь вы администратор этого сервера-сервиса dxdxdxd";
        assertEquals(expected, answer);

        answer = service.getOutput();
        expected = "Админом больше, админом меньше, какая разница.... ;DDDDDDDD";
        assertEquals(expected, answer);
    }

    @Test
    void addRecipeByAdminTest() {
        service.setInstruction(Arrays.asList(
                "/admin_on",
                "/admin_add_recipe",
                "тестовая котлета",
                "ингредиент_1 ингредиент_2",
                "/recipe_name",
                "тестовая котлета"));
        service.runInstruction(bot);

        service.getOutput();
        service.getOutput();
        service.getOutput();
        service.getOutput();
        service.getOutput();
        String answer = service.getOutput();
        String expected = "ингредиент_1 ингредиент_2";
        assertEquals(expected, answer);
    }

    @Test
    void removeRecipeByAdminTest() {
        service.setInstruction(Arrays.asList(
                "/admin_on",
                "/admin_add_recipe",
                "тестовая котлета",
                "ингредиент_1 ингредиент_2",
                "/admin_remove_recipe",
                "тестовая котлета",
                "/recipe_name",
                "тестовая котлета"
        ));
        service.runInstruction(bot);
        service.getOutput();
        service.getOutput();
        service.getOutput();
        service.getOutput();
        service.getOutput();
        service.getOutput();
        service.getOutput();

        String answer = service.getOutput();
        String expected = "К сожалению блюдо не найдено(";
        assertEquals(expected, answer);
    }
}
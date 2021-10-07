import com.google.gson.Gson;
import logic.cheif_cooker.Dish;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTests {
    @Test
    public void givenListOfMyClass_whenSerializing_thenCorrect() {
        Dish friedEggs = new Dish("Яишница", Arrays.asList("яйца"));
        Dish pancakes = new Dish("Блины", Arrays.asList("яйца", "тесто", "молоко"));

        List<Dish> list = Arrays.asList(pancakes, friedEggs);

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        String expectedString = "[{\"name\":\"Блины\",\"recipe\":[\"яйца\",\"тесто\"" +
                ",\"молоко\"]},{\"name\":\"Яишница\",\"recipe\":[\"яйца\"]}]";

        assertEquals(expectedString, jsonString);
    }
}

package brain.cheif_cooker;

import brain.cheif_cooker.Dish;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONWorker {
    public static void saveDishesToJSON(List<Dish> dishes, String path) {
        FileWriter file = null;
        Gson gson = new Gson();

        try {
            file = new FileWriter(path);
            String jsonString = gson.toJson(dishes);
            file.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Dish> loadDishesFromJSON(String path) {
        Gson gson = new Gson();
        Type listOfDishObject = new TypeToken<ArrayList<Dish>>() {
        }.getType();

        List<Dish> dishes = null;
        try {
            dishes = gson.fromJson(new FileReader(path), listOfDishObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dishes;
    }
}

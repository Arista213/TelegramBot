package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Dish;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для сериализации блюд.
 */
public class JSONService {
    private static final Gson gson = new Gson();
    private static FileWriter fileWriter;

    /**
     * Сохранить блюда в json.
     */
    public static void saveDishes(List<Dish> dishes, String path) {
        try {
            Type dishesType = new TypeToken<List<Dish>>() {
            }.getType();

            fileWriter = new FileWriter(path);
            String jsonString = gson.toJson(dishes, dishesType);
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Загрузить блюда из json.
     */
    public static List<Dish> loadDishes(String path) {
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

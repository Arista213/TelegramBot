package dao;

import model.Dish;

import java.util.List;

public abstract class DishDao
{
    /**
     * Добавить блюдо по названию.
     */
    public abstract Dish get(String title);

    /**
     * Получить все блюда.
     */
    public abstract List<Dish> getAll();

    /**
     * Добавить блюдо.
     */
    public abstract void save(Dish dish);

    /**
     * Обновить блюдо.
     */
    public abstract void update(String title, Dish dish);

    /**
     * Удалить блюдо.
     */
    public abstract void delete(Dish dish);
}

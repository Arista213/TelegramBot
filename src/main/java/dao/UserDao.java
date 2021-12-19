package dao;

import model.User;

import java.util.List;

public abstract class UserDao
{
    /**
     * Получить пользователя по id.
     */
    public abstract User get(long id);

    /**
     * Получить всех пользователей.
     */
    public abstract List<User> getAll();

    /**
     * Сохранить пользователя.
     */
    public abstract void save(User user);

    /**
     * Обновить пользователя.
     */
    public abstract void update(User user);

    /**
     * Удалить пользователя по id.
     */
    public abstract void delete(User user);
}

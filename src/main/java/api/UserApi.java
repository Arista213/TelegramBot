package api;

import model.Mode;
import model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Апи для работы с режимами пользователей.
 */
public abstract class UserApi {
    private static Map<User, Mode> users = new HashMap<>();

    /**
     * Добавить пользователя в базу данных.
     */
    public static void add(User user, Mode mode) {
        users.put(user, mode);
    }

    /**
     * Обновить режим пользователя.
     */
    public static void update(User user, Mode mode) {
        users.replace(user, mode);
    }

    /**
     * Проверить находится ли пользователь в режиме администратора.
     */
    public static boolean isAdmin(User user) {
        return users.get(user) == Mode.Admin;
    }

    /**
     * Количество всех пользователей.
     */
    public static int getUsersAmount() {
        return users.size();
    }

    /**
     * Удалить всех пользователей из базы данных.
     */
    public static void dropUsers() {
        users = new HashMap<>();
    }
}

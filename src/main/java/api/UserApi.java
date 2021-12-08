package api;

import message.MessageWaiter;
import message.model.IAction;
import model.Mode;
import model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Апи для работы с режимами пользователей.
 */
public abstract class UserApi {
    private static final Map<User, Mode> users = new HashMap<>();
    private static final Map<User, MessageWaiter> usersWaiters = new HashMap<>();

    /**
     * Добавить пользователя в базу данных.
     */
    public static void add(User user, Mode mode) {
        users.put(user, mode);
        usersWaiters.put(user, new MessageWaiter());
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
        users.clear();
    }

    /**
     * Проверяет находится ли пользователь в базе данных.
     */
    public static boolean isRegistered(User user) {
        return users.containsKey(user);
    }

    /**
     * Добавляет метод в очередь для пользователя.
     */
    public static void addToMessageWaiter(User user, IAction action) {
        usersWaiters.get(user).add(action);
    }

    public static MessageWaiter getMessageWaiter(User user) {
        return usersWaiters.get(user);
    }
}

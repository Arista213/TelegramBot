package IO.waiter;

import models.ICallback;
import models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, преналдежит только одному пользователю.
 * Отвечает за нажатие пользователем кнопки.
 */
public class CallbackWaiter {
    /**
     * Map с ключом - идентификатором(id) кнопки и значением - ICallback,
     * который будут использован, когда пользователь нажмёт на кнопку.
     */
    private final Map<String, ICallback> callbacks = new HashMap<>();
    private final User user;

    /**
     * Для каждой кнопки устанавливается уникальный идентификатор.
     */
    private Integer id = 0;

    public CallbackWaiter(User user) {
        this.user = user;
    }

    /**
     * @param callback, добавляется в
     * @return id кнопки, к который добавили ICallback.
     */
    public Integer add(ICallback callback) {
        id++;
        callbacks.put(id.toString(), callback);
        return id;
    }

    /**
     * Выполнит ICallback из Map по id.
     *
     * @param callData - id кнопки
     */
    public void execute(String callData) {
        callbacks.get(callData).execute(user);
    }
}

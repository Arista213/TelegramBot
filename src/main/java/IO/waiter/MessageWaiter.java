package IO.waiter;

import model.Message;
import model.User;
import model.IAction;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Класс, преналдежит только одному пользователю.
 * Отвечает за ожидание ввода пользователя.
 */
public class MessageWaiter
{
    /**
     * Очередь с действиями, которые будут использованы, когда пользователю введет что-то введет.
     */
    private final Queue<IAction> actions = new LinkedList<>();
    private final User user;

    public MessageWaiter(User user)
    {
        this.user = user;
    }

    /**
     * @param action Добавить в очередь IAction.
     */
    public void add(IAction action)
    {
        actions.add(action);
    }

    /**
     * Выполнит IAction из очереди и передаст аргументом ввод пользователя.
     * @param message ввод пользователя.
     */
    public void execute(Message message)
    {
        Objects.requireNonNull(actions.poll()).execute(user, message);
    }

    /**
     * @return ожидает ли программа ввода пользователя.
     */
    public boolean isWaiting()
    {
        return !actions.isEmpty();
    }
}

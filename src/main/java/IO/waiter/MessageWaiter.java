package IO.waiter;

import model.telegram.IAction;
import model.Message;
import model.User;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class MessageWaiter
{
    private final Queue<IAction> actions = new LinkedList<>();
    private final User user;

    public MessageWaiter(User user)
    {
        this.user = user;
    }

    public void add(IAction action)
    {
        actions.add(action);
    }

    public void execute(Message message)
    {
        Objects.requireNonNull(actions.poll()).execute(user, message);
    }

    public boolean isWaiting()
    {
        return !actions.isEmpty();
    }
}

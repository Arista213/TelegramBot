package message;

import model.User;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class MessageWaiter {
    private final Queue<IAction> actions = new LinkedList<>();

    public void add(IAction action) {
        actions.add(action);
    }

    public void execute(User user, Message message) {
        Objects.requireNonNull(actions.poll()).execute(user, message);
    }

    public boolean isWaiting() {
        return !actions.isEmpty();
    }
}

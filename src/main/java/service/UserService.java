package service;

import IO.waiter.CallbackWaiter;
import IO.waiter.MessageWaiter;
import model.IAction;
import model.ICallback;
import model.Mode;
import model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserService
{
    private final Set<User> users = new HashSet<>();
    private final Map<User, Mode> userModeMap = new HashMap<>();
    private final Map<User, CallbackWaiter> callbackWaiterMap = new HashMap<>();
    private final Map<User, MessageWaiter> messageWaiterMap = new HashMap<>();

    public void addUser(User user)
    {
        users.add(user);
        userModeMap.put(user, Mode.User);
        callbackWaiterMap.put(user, new CallbackWaiter(user));
        messageWaiterMap.put(user, new MessageWaiter(user));
    }

    public void setMode(User user, Mode mode)
    {
        if (!users.contains(user)) addUser(user);
        userModeMap.put(user, mode);
    }

    /**
     * Добавляет метод в очередь для пользователя.
     */
    public void addMessageWait(User user, IAction action)
    {
        if (!users.contains(user)) addUser(user);
        messageWaiterMap.get(user).add(action);
    }

    public void addCallbackWait(User user, ICallback callback)
    {
        if (!users.contains(user)) addUser(user);
        callbackWaiterMap.get(user).add(callback);
    }

    public MessageWaiter getMessageWaiter(User user)
    {
        if (!users.contains(user)) addUser(user);
        return messageWaiterMap.get(user);
    }

    public CallbackWaiter getCallbackWaiter(User user)
    {
        boolean a = users.contains(user);
        if (!users.contains(user)) addUser(user);
        return callbackWaiterMap.get(user);
    }

    public Mode getMode(User user)
    {
        if (!users.contains(user)) addUser(user);
        return userModeMap.get(user);
    }
}

package model;

import IO.waiter.CallbackWaiter;
import IO.waiter.MessageWaiter;

import java.util.Objects;

/**
 * Пользователь.
 */
public class User
{
    private final Long id;
    private final MessageWaiter messageWaiter = new MessageWaiter(this);
    private final CallbackWaiter callbackWaiter = new CallbackWaiter(this);
    private Mode mode;

    public User(Long id)
    {
        this.id = id;
        this.mode = Mode.User;
    }


    public Long getId()
    {
        return id;
    }

    public Mode getMode()
    {
        return mode;
    }

    public void setMode(Mode mode)
    {
        this.mode = mode;
    }

    /**
     * Добавляет метод в очередь для пользователя.
     */
    public void addMessageWait(IAction action)
    {
        messageWaiter.add(action);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    public MessageWaiter getMessageWaiter()
    {
        return messageWaiter;
    }

    public CallbackWaiter getCallbackWaiter()
    {
        return callbackWaiter;
    }
}

package model;

import IO.waiter.CallbackWaiter;
import IO.waiter.MessageWaiter;

import java.util.HashSet;
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
    private String diet;
    public final HashSet<String> intolerances;

    public User(Long id)
    {
        this.id = id;
        this.mode = Mode.User;
        this.diet = "";
        this.intolerances = new HashSet<>();
    }

    public String getDiet() { return diet; }

    public void setDiet(String diet) { this.diet = diet; }

    public String getIntolerancesString() { return String.join(",", intolerances); }

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
     * Получаем страницу пользователя
     * т.е. какую диету пользователь использует
     * и какую еду пользователь не переносит
     */
    public String getUserPage()
    {
        return "Diet: " + diet + "\n" + "Intolerances: " + getIntolerancesString();
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

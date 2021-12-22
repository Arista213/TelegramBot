package models;

/**
 * Интерфейс для работы с MessageWaiter.
 */
public interface IAction
{
    void execute(User user, Message message);
}

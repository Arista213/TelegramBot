package model;

import model.Message;
import model.User;

/**
 * Интерфейс для работы с MessageWaiter.
 */
public interface IAction
{
    void execute(User user, Message message);
}

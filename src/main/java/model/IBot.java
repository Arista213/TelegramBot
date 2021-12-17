package model;

/**
 * Функционал бота.
 */
public interface IBot
{
    void handleMessage(User user, Message message);
}

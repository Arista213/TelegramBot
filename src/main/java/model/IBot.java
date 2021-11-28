package model;

import message.model.Message;

/**
 * Функционал бота.
 */
public interface IBot {
    void handleMessage(User user, Message message);
}

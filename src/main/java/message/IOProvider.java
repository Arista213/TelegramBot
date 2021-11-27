package message;

import model.User;

/**
 * Абстракция, ответственная за принятие сообщений пользователя и отправления ему ответов.
 */
public interface IOProvider {
    void sendMessage(User user, Message message);
}

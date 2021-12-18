package IO.provider;

import model.Message;
import model.User;

/**
 * Абстракция, ответственная за принятие сообщений пользователя и отправления ему ответов.
 */
public interface IMessageProvider
{
    void sendMessage(User user, Message message);
}

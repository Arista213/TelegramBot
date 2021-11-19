package message;

/**
 * Абстракция, ответственная за принятие сообщений пользователя и отправления ему ответов.
 */
public abstract class MessageProvider {
    public abstract Message getMessage();

    public abstract void sendMessage(Message message);
}

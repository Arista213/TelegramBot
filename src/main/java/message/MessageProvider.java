package message;

/**
 * Абстракция, ответственная за принятие сообщений пользователя и отправления ему ответов.
 */
public abstract class MessageProvider {
    public Message getMessage() {
        return null;
    }

    public void sendMessage(Message message) {
    }
}

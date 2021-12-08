package message.model;

/**
 * Сущность сообщения для взаимодействия с пользователем.
 */
public class Message {
    private final String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

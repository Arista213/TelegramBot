package logic.communication;

/**
 * Сервис, ответственный за принятие сообщений пользователя и отправления ему ответов.
 */
public interface ICommunicateService {
    Message getMessage();

    void sendMessage(Message message);
}

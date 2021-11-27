package message;

import message.Message;
import model.User;

public interface IAction {
    void execute(User user, Message message);
}

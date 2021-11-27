package model;

import message.Message;

public class Request {
    private final User user;
    private final Message message;

    public Request(User user, Message message) {
        this.user = user;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}

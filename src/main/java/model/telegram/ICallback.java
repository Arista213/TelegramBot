package model.telegram;

import model.User;

public interface ICallback
{
    void execute(User user);
}

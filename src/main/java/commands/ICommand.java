package commands;

import model.User;

/**
 * Для реализации комманд для взаимодействия с ботом.
 */
public interface ICommand
{
    void process(User user);
}
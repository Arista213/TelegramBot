package brain.commands;

import brain.Bot;

/**
 * Для реализации комманд для взаимодействия с ботом
 */
public interface ICommand {
    void process(Bot bot);
}
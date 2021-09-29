package brain.commands;

import brain.Bot;

/**
 * Для реализации комманд для взаимодействия с ботом
 */
public abstract class Command {
    public abstract void process(Bot bot);
}
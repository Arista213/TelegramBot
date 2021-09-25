package brain;

/**
 * Для реализации комманд для взаимодействия с ботом
 */
public abstract class Command {
    public abstract void process(Bot bot);
}
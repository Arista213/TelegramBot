package commands;

import model.Bot;

/**
 * Абстракция команды.
 */
public abstract class Command implements ICommand {
    protected final Bot bot;

    public Command(Bot bot) {
        this.bot = bot;
    }
}

package commands;

import model.ChiefBot;

/**
 * Абстракция команды.
 */
public abstract class Command implements ICommand {
    protected final ChiefBot bot;

    public Command(ChiefBot bot) {
        this.bot = bot;
    }
}

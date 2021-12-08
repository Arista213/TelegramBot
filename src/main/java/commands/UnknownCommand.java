package commands;

import constants.Commands;
import model.ChiefBot;
import model.User;

/**
 * Вывести, что комманда боту неизвестна.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, Commands.UNKNOWN_COMMAND.toStringValue());
    }
}

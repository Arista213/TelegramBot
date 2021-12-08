package commands;

import constants.Commands;
import model.ChiefBot;
import model.User;

/**
 * Выводит комманды для взаимодействия с ботом.
 */
public class Help extends Command {
    public Help(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, Commands.HELP.toStringValue());
    }
}

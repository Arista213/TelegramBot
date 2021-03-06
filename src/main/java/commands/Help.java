package commands;

import constants.CommandsOutput;
import models.ChiefBot;
import models.Message;
import models.User;

/**
 * Выводит комманды для взаимодействия с ботом.
 */
public class Help extends Command {
    public Help(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, new Message(CommandsOutput.HELP.toStringValue()));
    }
}

package commands;

import constants.CommandsOutput;
import model.ChiefBot;
import model.Message;
import model.User;

/**
 * Выводит комманды для взаимодействия с ботом.
 */
public class Help extends Command
{
    public Help(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        bot.setOutput(user, new Message(CommandsOutput.HELP.toStringValue()));
    }
}

package commands;

import constants.CommandsOutput;
import model.ChiefBot;
import model.Message;
import model.User;

/**
 * Комманда для начала работы с пользователем.
 */
public class Start extends Command
{
    public Start(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        bot.setOutput(user, new Message(CommandsOutput.START.toStringValue()));
    }
}

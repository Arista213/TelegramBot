package commands;

import constants.CommandsOutput;
import message.model.Message;
import model.ChiefBot;
import model.Mode;
import model.User;

/**
 * Переход в режим администратора.
 */
public class AdminMode extends Command
{
    public AdminMode(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        if (user.getMode() == Mode.User)
        {
            user.setMode(Mode.Admin);
            bot.setOutput(user, new Message(CommandsOutput.ADMIN_MODE.toStringValue()));
        }
        else
        {
            bot.setOutput(user, new Message(CommandsOutput.ALREADY_ADMIN.toStringValue()));
        }
    }
}

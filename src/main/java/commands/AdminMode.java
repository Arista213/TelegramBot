package commands;

import constants.CommandsOutput;
import models.ChiefBot;
import models.Message;
import models.Mode;
import models.User;

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
        if (userService.getMode(user) == Mode.User)
        {
            userService.setMode(user, Mode.Admin);
            bot.setOutput(user, new Message(CommandsOutput.ADMIN_MODE.toStringValue()));
        }
        else
        {
            bot.setOutput(user, new Message(CommandsOutput.ALREADY_ADMIN.toStringValue()));
        }
    }
}

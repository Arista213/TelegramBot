package commands;

import constants.CommandsOutput;
import models.ChiefBot;
import models.Message;
import models.Mode;
import models.User;

/**
 * Переход в режим пользователя.
 */
public class UserMode extends Command {
    public UserMode(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        if (userService.getMode(user) == Mode.Admin) {
            userService.setMode(user, Mode.User);
            bot.setOutput(user, new Message(CommandsOutput.USER_MODE.toStringValue()));
        } else {
            bot.setOutput(user, new Message(CommandsOutput.ALREADY_USER.toStringValue()));
        }
    }
}


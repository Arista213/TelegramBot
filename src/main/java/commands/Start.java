package commands;

import constants.CommandsOutput;
import models.ChiefBot;
import models.Message;
import models.User;

/**
 * Комманда для начала работы с пользователем.
 */
public class Start extends Command {
    public Start(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, new Message(CommandsOutput.START.toStringValue()));
    }
}

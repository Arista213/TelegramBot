package commands;

import constants.Commands;
import message.model.Message;
import model.ChiefBot;
import model.User;

/**
 * Комманда для начала работы с пользователем.
 */
public class Start extends Command {
    public Start(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, new Message(Commands.START.toStringValue()));
    }
}

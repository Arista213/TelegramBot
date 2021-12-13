package commands;

import api.UserApi;
import constants.Commands;
import message.model.Message;
import model.ChiefBot;
import model.Mode;
import model.User;

/**
 * Переход в режим администратора.
 */
public class AdminMode extends Command {
    public AdminMode(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        if (!UserApi.isAdmin(user)) {
            UserApi.update(user, Mode.Admin);
            bot.setOutput(user, new Message(Commands.ADMIN_MODE.toStringValue()));
        } else {
            bot.setOutput(user, new Message(Commands.ALREADY_ADMIN.toStringValue()));
        }
    }
}

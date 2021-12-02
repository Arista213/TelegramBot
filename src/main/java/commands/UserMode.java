package commands;

import api.UserApi;
import constants.Commands;
import model.ChiefBot;
import model.Mode;
import model.User;

/**
 * Переход в режим пользователя.
 */
public class UserMode extends Command {
    public UserMode(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        if (UserApi.isAdmin(user)) {
            UserApi.update(user, Mode.User);
            bot.setOutput(user, Commands.USER_MODE.toStringValue());
        } else {
            bot.setOutput(user, Commands.ALREADY_USER.toStringValue());
        }
    }
}


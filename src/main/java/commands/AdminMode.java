package commands;

import constants.Commands;
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
        if (user.getMode() == Mode.User) {
            user.setMode(Mode.Admin);
            bot.setOutput(user, Commands.ADMIN_MODE.toStringValue());
        } else {
            bot.setOutput(user, Commands.ALREADY_ADMIN.toStringValue());
        }
    }
}

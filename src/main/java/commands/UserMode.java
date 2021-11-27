package commands;

import model.ChiefBot;
import model.Mode;
import model.User;
import api.UserApi;

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
            bot.setOutput(user, "Вы больше не в режиме администратора");
        } else {
            bot.setOutput(user, "Вы уже в пользовательском режиме");
        }
    }
}


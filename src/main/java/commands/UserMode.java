package commands;

import model.Bot;
import model.Mode;
import model.User;
import api.UserApi;

/**
 * Переход в режим пользователя.
 */
public class UserMode extends Command {
    public UserMode(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        if (UserApi.isAdmin(user)) {
            UserApi.update(user, Mode.User);
            bot.setOutput("Вы больше не в режиме администратора");
        } else {
            bot.setOutput("Вы уже в пользовательском режиме");
        }
    }
}


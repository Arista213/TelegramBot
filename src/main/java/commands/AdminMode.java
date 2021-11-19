package commands;

import model.Bot;
import model.Mode;
import model.User;
import api.UserApi;

/**
 * Переход в режим администратора.
 */
public class AdminMode extends Command {
    public AdminMode(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        if (!UserApi.isAdmin(user)) {
            UserApi.update(user, Mode.Admin);
            bot.setOutput("Теперь вы в режиме администратора");
        } else {
            bot.setOutput("Вы уже админ");
        }
    }
}

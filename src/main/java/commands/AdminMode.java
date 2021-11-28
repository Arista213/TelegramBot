package commands;

import api.UserApi;
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
            bot.setOutput(user, "Теперь вы в режиме администратора");
        } else {
            bot.setOutput(user, "Вы уже админ");
        }
    }
}

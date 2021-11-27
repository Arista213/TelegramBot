package commands;

import model.ChiefBot;
import model.Mode;
import model.User;
import api.UserApi;

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
            bot.setOutput(user,"Теперь вы в режиме администратора");
        } else {
            bot.setOutput(user, "Вы уже админ");
        }
    }
}

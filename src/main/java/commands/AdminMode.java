package commands;

import model.Bot;

/**
 * Переход в режим администратора.
 */
public class AdminMode extends Command {
    public AdminMode(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        if (!bot.getUser().isAdmin()) {
            bot.getUser().switchAdminStatus();
            bot.setOutput("Теперь вы администратор этого сервера-сервиса dxdxdxd");
        } else {
            bot.setOutput("Вы уже админ");
        }
    }
}

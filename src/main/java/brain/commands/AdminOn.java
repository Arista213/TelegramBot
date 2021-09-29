package brain.commands;

import brain.Bot;

public class AdminOn extends Command {
    @Override
    public void process(Bot bot) {
        if (!bot.user.isAdmin()) {
            bot.user.switchAdminStatus();
            bot.setOutput("Теперь вы администратор этого сервера-сервиса dxdxdxd");
        }
        else {
            bot.setOutput("Вы уже админ");
        }
    }
}

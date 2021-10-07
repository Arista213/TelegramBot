package logic.commands;

import logic.Bot;

public class AdminOn implements ICommand {
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

package brain.commands;

import brain.Bot;

public class AdminOff extends Command {
    @Override
    public void process(Bot bot) {
        if (bot.user.isAdmin()) {
            bot.user.switchAdminStatus();
            bot.setOutput("Админом больше, админом меньше, какая разница.... ;DDDDDDDD");
        }
        else {
            bot.setOutput("Да вы и так не админ, успокойтесь блин :)");
        }
    }
}


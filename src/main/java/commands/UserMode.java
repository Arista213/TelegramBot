package commands;

import model.Bot;

/**
 * Переход в режим пользователя.
 */
public class UserMode extends Command {
    public UserMode(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        if (bot.getUser().isAdmin()) {
            bot.getUser().switchAdminStatus();
            bot.setOutput("Админом больше, админом меньше, какая разница.... ;DDDDDDDD");
        } else {
            bot.setOutput("Да вы и так не админ, успокойтесь блин :)");
        }
    }
}


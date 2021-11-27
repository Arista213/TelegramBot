package commands;

import model.ChiefBot;
import model.User;

/**
 * Вывести, что комманда боту неизвестна.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, "Неизвестная комманда");
    }
}

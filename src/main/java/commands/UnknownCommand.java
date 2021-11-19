package commands;

import model.Bot;
import model.User;

/**
 * Вывести, что комманда боту неизвестна.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput("Неизвестная комманда");
    }
}

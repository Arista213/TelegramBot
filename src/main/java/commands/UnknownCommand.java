package commands;

import model.Bot;

/**
 * Вывести, что комманда боту неизвестна.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        bot.setOutput("Неизвестная комманда");
    }
}

package brain.commands;

import brain.Bot;

public class UnknownCommand implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Неизвестная комманда");
    }
}

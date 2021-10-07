package logic.commands;

import logic.Bot;

public class UnknownCommand implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Неизвестная комманда");
    }
}

package logic.commands;

import logic.Bot;

public class Start implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Шеф-повар здесь!\n" +
                "Напиши мне /help и я расскажу, что я умею\n");
    }
}

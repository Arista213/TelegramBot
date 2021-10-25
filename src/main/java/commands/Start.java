package commands;

import model.Bot;

/**
 * Комманда для начала работы с пользователем.
 */
public class Start extends Command {
    public Start(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        bot.setOutput("Шеф-повар здесь!\n" +
                "Напиши мне /help и я расскажу, что умею\n");
    }
}

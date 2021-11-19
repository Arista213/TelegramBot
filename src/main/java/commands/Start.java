package commands;

import model.Bot;
import model.User;

/**
 * Комманда для начала работы с пользователем.
 */
public class Start extends Command {
    public Start(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput("Шеф-повар здесь!\n" +
                "Напиши мне /help и я расскажу, что умею\n");
    }
}

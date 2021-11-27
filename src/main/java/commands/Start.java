package commands;

import model.ChiefBot;
import model.User;

/**
 * Комманда для начала работы с пользователем.
 */
public class Start extends Command {
    public Start(ChiefBot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput(user, "Шеф-повар здесь!\n" +
                "Напиши мне /help и я расскажу, что умею\n");
    }
}

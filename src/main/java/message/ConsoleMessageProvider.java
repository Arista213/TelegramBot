package message;

import constants.Config;
import model.ChiefBot;
import model.Mode;
import model.User;
import api.UserApi;

import java.util.Scanner;

/**
 * Реализация абстракции MessageProvider предназначенный для работы с консолью.
 */
public class ConsoleMessageProvider implements IOProvider {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Начинает работу пользователя с консолью.
     */
    public void start() {
        User consoleUser = new User(0L);
        ChiefBot bot = new ChiefBot(this);
        UserApi.add(consoleUser, Mode.User);
        System.out.println("BOT_NAME: " + Config.BOT_NAME.toStringValue());
        System.out.println("TOKEN: " + Config.BOT_TOKEN.toStringValue());

        while (true) {
            String input = scanner.nextLine();
            bot.handleMessage(consoleUser, new Message(input));
        }
    }

    /**
     * Печатает вывод бота в консоль.
     */
    @Override
    public void sendMessage(User user, Message message) {
        System.out.println(message.getText());
    }
}

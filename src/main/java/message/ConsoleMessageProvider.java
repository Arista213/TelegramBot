package message;

import constants.Configuration;
import model.Bot;
import model.Mode;
import model.User;
import api.UserApi;

import java.util.Scanner;

/**
 * Реализация абстракции MessageProvider предназначенный для работы с консолью.
 */
public class ConsoleMessageProvider extends MessageProvider {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Начинает работу пользователя с консолью.
     */
    public void start() {
        User consoleUser = new User(0);
        Bot bot = new Bot(this);
        UserApi.add(consoleUser, Mode.User);
        System.out.println("BOT_NAME: " + Configuration.BOT_NAME.toStringValue());
        System.out.println("TOKEN: " + Configuration.BOT_TOKEN.toStringValue());

        while (true) {
            String input = scanner.nextLine();
            bot.runCommand(input, consoleUser);
        }
    }

    /**
     * Считывает ввод пользователя из консоли.
     */
    @Override
    public Message getMessage() {
        return new Message(scanner.nextLine());
    }

    /**
     * Печатает вывод бота в консоль.
     */
    @Override
    public void sendMessage(Message message) {
        System.out.println(message.getText());
    }
}

package message;

import constants.Configuration;
import model.Bot;
import model.User;

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
        var bot = new Bot(this, new User());
        System.out.println("BOT_NAME: " + Configuration.BOT_NAME.toStringValue());
        System.out.println("TOKEN: " + Configuration.BOT_TOKEN.toStringValue());

        for (; ; ) {
            String input = scanner.nextLine();
            bot.runCommand(input);
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

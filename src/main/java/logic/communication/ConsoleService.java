package logic.communication;

import logic.Bot;
import logic.User;

import java.util.Scanner;

/**
 * Сервис предназначенный для работы с консолью
 */
public class ConsoleService implements ICommunicateService {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        var bot = new Bot(this, new User());

        for (; ; ) {
            String input = scanner.nextLine();
            bot.runCommand(input);
        }
    }

    @Override
    public Message getMessage() {
        return new Message(scanner.nextLine());
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println(message.text);
    }
}

import message.ConsoleMessageProvider;
import message.TelegramMessageProvider;

public class Main {
    public static void main(String[] args) {
//        new ConsoleMessageProvider().start();
        new TelegramMessageProvider().start();
    }
}

import message.impl.TelegramMessageProvider;

public class Main {
    public static void main(String[] args) {
//        new ConsoleMessageProvider().start();
        new TelegramMessageProvider().start();
    }
}

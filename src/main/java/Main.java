import brain.Bot;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();
        Scanner scanner = new Scanner(System.in);
        String answer;

        Thread thread = new Thread(() -> sendMessages(scanner, bot));
        thread.start();

        while (true) {
            answer = bot.waitForOutput();
            System.out.println(answer);
        }
    }

    /**
     * Отправка сообщений боту в потоке
     * @param scanner
     * @param bot
     */
    private static void sendMessages(Scanner scanner, Bot bot) {

        while (!Thread.interrupted()) {
            String message = scanner.nextLine();
            bot.receive(message);
        }
    }
}

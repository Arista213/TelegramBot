import brain.Bot;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();
        String answer;


        bot.start();
        Scanner scanner = new Scanner(System.in);
        bot.receive(scanner.nextLine());

        Thread thread = new Thread(() -> sendMessages(bot));
        thread.start();

        while (true) {
            answer = bot.getOutput();
            if (answer == null) continue;
            System.out.println(answer);
        }
    }

    private static void sendMessages(Bot bot) {
        while (!Thread.interrupted()) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            bot.receive(message);
        }
    }
}

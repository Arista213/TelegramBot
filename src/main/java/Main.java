import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();
        String answer;
        bot.Start();

        Scanner scanner = new Scanner(System.in);

        do {
            String message = scanner.nextLine();
            answer = bot.receive(message);
            System.out.println(answer);
        } while (true);
    }
}

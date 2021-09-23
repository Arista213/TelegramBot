import Functionality.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();
        String answer;
        bot.start();

        Scanner scanner = new Scanner(System.in);

        do {
            String message = scanner.nextLine();
            answer = bot.receive(message);
            System.out.println(answer);
        } while (true);
    }
}

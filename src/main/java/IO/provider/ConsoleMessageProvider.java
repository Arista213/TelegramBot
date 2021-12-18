package IO.provider;

import constants.Config;
import dao.DishDao;
import dao.UserDao;
import dao.impl.SimpleDishDao;
import dao.impl.SimpleUserDao;
import model.ChiefBot;
import model.Message;
import model.User;
import service.DishService;

import java.util.Scanner;

/**
 * Реализация абстракции MessageProvider предназначенный для работы с консолью.
 */
public class ConsoleMessageProvider implements IMessageProvider
{
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Начинает работу пользователя с консолью.
     */
    public void start()
    {
        User consoleUser = new User(0L);
        DishDao dishDao = new SimpleDishDao();
        UserDao userDao = new SimpleUserDao();
        DishService dishService = new DishService(dishDao);

        ChiefBot bot = new ChiefBot(this, dishDao, dishService);
        userDao.save(consoleUser);
        System.out.println("BOT_NAME: " + Config.BOT_NAME.toStringValue());
        System.out.println("TOKEN: " + Config.BOT_TOKEN.toStringValue());

        while (true)
        {
            String input = scanner.nextLine();
            bot.handleMessage(consoleUser, new Message(input));
        }
    }

    /**
     * Печатает вывод бота в консоль.
     */
    @Override
    public void sendMessage(User user, Message message)
    {
        System.out.println(message.getText());
    }
}

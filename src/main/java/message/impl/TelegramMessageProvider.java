package message.impl;

import dao.DishDao;
import dao.impl.SimpleDishDao;
import message.model.IMessageProvider;
import message.model.Message;
import model.ChiefBot;
import model.IBot;
import model.TelegramBot;
import model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.DishService;

/**
 * Реализация IMessageProvider предназначенный для работы с телеграм ботом.
 */
public final class TelegramMessageProvider implements IMessageProvider {
    private static SendMessage messageSender;
    private static TelegramBot telegramBot;

    /**
     * Запускает бота.
     */
    public void start() {
        DishDao dishDao = new SimpleDishDao();
        DishService dishService = new DishService(dishDao);
        IBot bot = new ChiefBot(this, dishDao, dishService);
        messageSender = new SendMessage();
        telegramBot = new TelegramBot(bot);
    }

    @Override
    public void sendMessage(User user, Message message) {
        messageSender.setChatId(user.getId().toString());
        messageSender.setText(message.getText());
        try {
            telegramBot.execute(messageSender);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
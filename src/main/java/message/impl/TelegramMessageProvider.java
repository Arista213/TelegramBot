package message.impl;

import dao.DishDao;
import dao.impl.SimpleDishDao;
import message.model.IMessageProvider;
import message.model.Message;
import model.ChiefBot;
import model.IBot;
import model.TelegramBot;
import model.User;
import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.DishService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Реализация IMessageProvider предназначенный для работы с телеграм ботом.
 */
public final class TelegramMessageProvider implements IMessageProvider
{
    private SendMessage messageSender;
    private SendPhoto photoSender;
    private TelegramBot telegramBot;

    /**
     * Запускает бота.
     */
    public void start()
    {
        DishDao dishDao = new SimpleDishDao();
        DishService dishService = new DishService(dishDao);
        IBot bot = new ChiefBot(this, dishDao, dishService);
        messageSender = new SendMessage();
        photoSender = new SendPhoto();
        telegramBot = new TelegramBot(bot);
    }

    @Override
    public void sendMessage(User user, Message message)
    {
        if (message.getImageURL() != null) sendPhoto(user, message);
        messageSender.setChatId(user.getId().toString());
        messageSender.setText(message.getText());
        try
        {
            telegramBot.execute(messageSender);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    private void sendPhoto(User user, Message message)
    {
        URL url = message.getImageURL();
        File file = new File("target" + System.getProperty("file.separator") + "TempFile");
        try
        {
            FileUtils.copyURLToFile(url, file);
            InputFile photo = new InputFile(file);
            photoSender.setChatId(user.getId().toString());
            photoSender.setPhoto(photo);
            telegramBot.execute(photoSender);
        }
        catch (IOException | TelegramApiException e)
        {
            e.printStackTrace();
        }
        finally
        {
            file.delete();
        }
    }
}
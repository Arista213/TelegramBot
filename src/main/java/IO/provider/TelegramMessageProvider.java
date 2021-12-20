package IO.provider;

import IO.waiter.CallbackWaiter;
import dao.DishDao;
import dao.impl.SimpleDishDao;
import model.ChiefBot;
import model.IBot;
import model.Message;
import model.User;
import model.Button;
import model.TelegramBot;
import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import service.DishService;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        try
        {
            if (message.getImageURL() != null) sendPhoto(user, message);
            if (message.getButtons() != null)
                messageSender.setReplyMarkup(getMarkupInLine(user, message.getButtons()));

            messageSender.setChatId(user.getId().toString());
            messageSender.setText(message.getText());
            telegramBot.execute(messageSender);
            messageSender = new SendMessage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void sendPhoto(User user, Message message) throws Exception
    {
        URL url = message.getImageURL();
        File file = new File("target" + System.getProperty("file.separator") + "TempFile");
        FileUtils.copyURLToFile(url, file);
        InputFile photo = new InputFile(file);
        photoSender.setChatId(user.getId().toString());
        photoSender.setPhoto(photo);
        telegramBot.execute(photoSender);
        if (!file.delete()) throw new Exception("Картинка не была удалена");
    }

    private InlineKeyboardMarkup getMarkupInLine(User user, List<List<Button>> buttons)
    {
        CallbackWaiter callbackWaiter = user.getCallbackWaiter();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (List<Button> messageButtonRow : buttons)
        {
            List<InlineKeyboardButton> inlineKeyboardButtonsRow = new ArrayList<>();
            for (Button button : messageButtonRow)
            {
                Integer id = callbackWaiter.add(button.getCallback());
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(button.getText());
                inlineKeyboardButton.setCallbackData(id.toString());

                inlineKeyboardButtonsRow.add(inlineKeyboardButton);
            }
            keyboard.add(inlineKeyboardButtonsRow);
        }

        markupInline.setKeyboard(keyboard);
        return markupInline;
    }
}


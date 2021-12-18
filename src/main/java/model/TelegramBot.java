package model;

import constants.Commands;
import constants.CommandsOutput;
import constants.Config;
import dao.UserDao;
import dao.impl.SimpleUserDao;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Телеграм бот.
 */
public final class TelegramBot extends TelegramLongPollingBot
{
    private final IBot bot;
    private final UserDao userDao;

    public TelegramBot(IBot bot)
    {
        this.bot = bot;

        try
        {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
        userDao = new SimpleUserDao();
    }

    @Override
    public String getBotUsername()
    {
        return Config.BOT_NAME.toStringValue();
    }

    @Override
    public String getBotToken()
    {
        return Config.BOT_TOKEN.toStringValue();
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage())
        {
            String userName = update.getMessage().getChatId().toString();
            System.out.println(userName + ":\t" + update.getMessage().getText());
            Message message = new Message(update.getMessage().getText());
            long userId = update.getMessage().getChatId();
            if (message.getText().equalsIgnoreCase(Commands.START.toStringValue()))
            {
                Start(userId);
            }
            else
            {
                User user = userDao.get(userId);
                bot.handleMessage(user, message);
            }
        }

        if (update.hasCallbackQuery())
        {
            String callData = update.getCallbackQuery().getData();
            User user = userDao.get(update.getCallbackQuery().getMessage().getChatId());
            user.getCallbackWaiter().execute(callData);
        }
    }

    /**
     * Регистрация нового пользователя
     */
    private void Start(long userId)
    {
        User user = new User(userId);
        userDao.save(user);
        sendKeyboardToUser(user);
    }

    /**
     * Выдача пользователю клавиатуры.
     */
    private void sendKeyboardToUser(User user)
    {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRow = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        row1.add(Commands.HELP.toStringValue());
        row2.add(Commands.DISH_BY_TITLE.toStringValue());
        row3.add(Commands.DISHES_BY_PRODUCTS.toStringValue());
        keyboardRow.add(row1);
        keyboardRow.add(row2);
        keyboardRow.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboardRow);

        SendMessage sm = new SendMessage();
        sm.setText(CommandsOutput.START.toStringValue());
        sm.setChatId(user.getId().toString());
        sm.setReplyMarkup(replyKeyboardMarkup);
        try
        {
            execute(sm);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}

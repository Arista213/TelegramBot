package model;

import api.UserApi;
import constants.Commands;
import constants.CommandsOutput;
import constants.Config;
import message.model.Message;
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
public final class TelegramBot extends TelegramLongPollingBot {
    private final IBot bot;

    public TelegramBot(IBot bot) {
        this.bot = bot;

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return Config.BOT_NAME.toStringValue();
    }

    @Override
    public String getBotToken() {
        return Config.BOT_TOKEN.toStringValue();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String userName = update.getMessage().getChatId().toString();
            System.out.println(userName + ":\t" + update.getMessage().getText());
            Message message = new Message(update.getMessage().getText());
            long userId = update.getMessage().getChatId();
            if (message.getText().equalsIgnoreCase(Commands.START.toStringValue())) {
                Start(userId);
            } else {
                User user = new User(userId); //TODO
                bot.handleMessage(user, message);
            }
        }
    }

    /**
     * Регистрация нового пользователя
     */
    private void Start(long userId) {
        User user = new User(userId);
        UserApi.add(user, Mode.User);
        sendKeyboardToUser(user);
        //TODO UserDao.save(userId, user);
    }

    /**
     * Выдача пользователю клавиатуры.
     */
    private void sendKeyboardToUser(User user) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRow = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add(Commands.HELP.toStringValue());
        row.add(Commands.DISH_BY_TITLE.toStringValue());
        row.add(Commands.DISHES_BY_PRODUCTS.toStringValue());
        keyboardRow.add(row);
        replyKeyboardMarkup.setKeyboard(keyboardRow);

        SendMessage sm = new SendMessage();
        sm.setText(CommandsOutput.START.toStringValue());
        sm.setChatId(user.getId().toString());
        sm.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

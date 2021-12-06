package model;

import constants.Config;
import dao.UserDao;
import dao.impl.SimpleUserDao;
import message.model.Message;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Телеграм бот.
 */
public final class TelegramBot extends TelegramLongPollingBot {
    private final IBot bot;
    private final UserDao userDao;

    public TelegramBot(IBot bot) {
        this.bot = bot;

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        userDao = new SimpleUserDao();
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
            var message = new Message(update.getMessage().getText());
            System.out.println(update.getMessage().getChatId().toString() + ": " + message.getText());
            if (message.getText().equalsIgnoreCase("/start")) {
                long userId = update.getMessage().getChatId();
                Start(userId);
            } else {
                var user = userDao.get(update.getMessage().getChatId());
                bot.handleMessage(user, message);
            }
        }
    }

    private void Start(long userId) {
        User user = new User(userId);
        userDao.save(user);
        bot.handleMessage(user, new Message("/start"));
        //TODO
    }
}

package model;

import api.UserApi;
import constants.Config;
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
            var user = new User(update.getMessage().getChatId());
            var message = new Message(update.getMessage().getText());

            if (!UserApi.isRegistered(user)) UserApi.add(user, Mode.User);
            bot.handleMessage(user, message);
        }
    }
}

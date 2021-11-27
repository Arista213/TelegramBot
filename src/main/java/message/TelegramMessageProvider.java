package message;

import api.UserApi;
import model.ChiefBot;
import model.Mode;
import model.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public final class TelegramMessageProvider extends TelegramLongPollingBot implements IOProvider {
    private static SendMessage messageSender;
    private static ChiefBot bot;

    public void start() {
        messageSender = new SendMessage();
        bot = new ChiefBot(this);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            System.out.println(update.getMessage().getText());
            var user = new User(update.getMessage().getChatId());
            if (!UserApi.isRegistered(user)) UserApi.add(user, Mode.User);
            bot.handleMessage(user, new Message(update.getMessage().getText()));
        }
    }

    @Override
    public String getBotUsername() {
        return "Chief-Bot";
    }

    @Override
    public String getBotToken() {
        return "1881683881:AAFpOdIJPVsY0jvIYE7QkjEHc2jq0AL3rA8";
    }


    @Override
    public void sendMessage(User user, Message message) {
        messageSender.setChatId(user.getId().toString());
        messageSender.setText(message.getText());
        try {
            execute(messageSender);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
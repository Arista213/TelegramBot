import constants.Commands;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Test
{
    public static void main(String[] args) throws TelegramApiException
    {
        Object[] enums = Commands.START.getDeclaringClass().getEnumConstants();
        for (int i = 0; i < enums.length; i++)
        {
            Commands commands = (Commands)enums[i];
            System.out.println(commands.toStringValue());
        }
//        var tba = new TelegramBotsApi(DefaultBotSession.class);
//        tba.registerBot(new TestHandler());
    }
}

import message.impl.ConsoleMessageProvider;
import message.impl.TelegramMessageProvider;

public class Main
{
    public static void main(String[] args)
    {
        String botMode = args[0];
        if (botMode.equalsIgnoreCase("Console"))
        {
            new ConsoleMessageProvider().start();
        }
        else
            new TelegramMessageProvider().start();
    }
}

package brain;

public class Start extends Command {
    @Override
    public void process(Bot bot) {
        bot.setOutput("Bot has started!\n" +
                "\n" +
                "Here's what bot can do\n" +
                "/start - starts bot\n" +
                "/hello - bot says hello\n");
    }
}

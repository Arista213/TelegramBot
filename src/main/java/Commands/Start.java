package Commands;

public class Start extends Command {
    @Override
    public String process() {
        return "Bot has started!\n" +
                "\n" +
                "Here's what bot can do\n" +
                "/start - starts bot\n" +
                "/hello - bot says hello\n";
    }
}

package brain;

public class Hello extends Command {
    @Override
    public void process(Bot bot) {
        bot.setOutput("Bot says hello!");
    }
}

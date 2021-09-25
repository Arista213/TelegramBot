package brain;

public class RecipeByName extends Command {

    @Override
    public void process(Bot bot) {
        bot.commandInRunning = true;
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        String name = null;
        while (name == null)
            name = bot.getMessage();

        bot.setOutput("Вы хотите приготовить " + name);
        bot.commandInRunning = false;
    }
}

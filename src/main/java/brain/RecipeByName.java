package brain;

public class RecipeByName extends Command {

    @Override
    public void process(Bot bot) {
        bot.commandInRunning = true;
        bot.setOutput("Введите название блюда, которое вы хотите приготовить");
        while (bot.getMessage() == null) {

        }
        bot.setOutput("Вы хотите приготовить " + bot.getMessage());
        bot.commandInRunning = false;
    }
}

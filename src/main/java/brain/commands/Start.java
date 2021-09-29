package brain.commands;

import brain.Bot;

public class Start extends Command {
    @Override
    public void process(Bot bot) {
        bot.setOutput("Шеф-повар здесь!\n" +
                "\n" +
                "Вот то, что я умею делать бот\n" +
                "/recipe_name - рецепт блюда по его названию\n" +
                "/recipe_ingredients - рецепт блюд, которые " +
                "можно приготовить при имеющихся ингредиентах\n");
    }
}

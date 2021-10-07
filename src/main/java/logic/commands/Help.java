package logic.commands;

import logic.Bot;

public class Help implements ICommand {
    public void process(Bot bot) {
        bot.setOutput("Вот то, что я умею делать\n" +
                "/recipe_name - рецепт блюда по его названию\n" +
                "/recipe_ingredients - рецепт блюд, которые " +
                "можно приготовить при имеющихся ингредиентах\n");
    }
}

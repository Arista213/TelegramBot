package commands;

import model.Bot;

/**
 * Выводит комманды для взаимодействия с ботом.
 */
public class Help extends Command {
    public Help(Bot bot) {
        super(bot);
    }

    @Override
    public void process() {
        bot.setOutput("Вот то, что я умею делать\n" +
                "/dish_by_title - рецепт блюда по его названию\n" +
                "/dishes_by_products - рецепт блюд, которые " +
                "можно приготовить при имеющихся ингредиентах\n");
    }
}

package commands;

import model.Bot;
import model.User;

/**
 * Выводит комманды для взаимодействия с ботом.
 */
public class Help extends Command {
    public Help(Bot bot) {
        super(bot);
    }

    @Override
    public void process(User user) {
        bot.setOutput("Вот то, что я умею делать\n" +
                "/dish_by_title - рецепт блюда по его названию\n" +
                "/dishes_by_products - рецепт блюд, которые " +
                "можно приготовить при имеющихся ингредиентах\n");
    }
}

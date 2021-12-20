package commands;

import constants.CommandsOutput;
import constants.Diets;
import constants.Intolerances;
import model.Button;
import model.ChiefBot;
import model.Message;
import model.User;

import java.util.List;

/**
 * Выводит страничку пользователя, которая содержит диету и блюда, котороые пользователь не переносит
 */
public class UserPage extends Command
{
    public UserPage(ChiefBot bot)
    {
        super(bot);
    }

    @Override
    public void process(User user)
    {
        Message message = new Message(CommandsOutput.SHOW_USER_PAGE.toStringValue() + "\n" + userService.getUserPage(user))
                .setButtons(List.of(List.of(new Button("Change diet", x -> setDiet(user)),
                        new Button("Change intolerances", x -> setIntolerances(user)))));
        bot.setOutput(user, message);
    }

    /**
     * Пишет сообщение с кнопками, с помощью которых можно заполнить информацию о блюдах,
     * которые пользователь не переносит
     */
    private void setIntolerances(User user)
    {
        List<List<Button>> buttons = List.of(
                List.of(
                        new Button(Intolerances.DAIRY.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.DAIRY.toStringValue())),
                        new Button(Intolerances.EGG.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.EGG.toStringValue())),
                        new Button(Intolerances.GLUTEN.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.GLUTEN.toStringValue()))),
                List.of(
                        new Button(Intolerances.SEAFOOD.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.SEAFOOD.toStringValue())),
                        new Button(Intolerances.SESAME.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.SESAME.toStringValue())),
                        new Button(Intolerances.SHELLFISH.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.SHELLFISH.toStringValue()))),

                List.of(
                        new Button(Intolerances.TREE_NUT.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.TREE_NUT.toStringValue())),
                        new Button(Intolerances.WHEAT.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.WHEAT.toStringValue())),
                        new Button(Intolerances.SOY.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.SOY.toStringValue()))),
                List.of(
                        new Button(Intolerances.SULFATE.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.SULFATE.toStringValue())),
                        new Button(Intolerances.GRAIN.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.GRAIN.toStringValue())),
                        new Button(Intolerances.PEANUT.toStringValue(),
                                x -> changeIntolerances(user, Intolerances.PEANUT.toStringValue()))),
                List.of(
                        new Button("Remove all my intolerances", x -> removeIntolerances(user))));

        Message message = new Message(CommandsOutput.CHOOSE_INTOLERANCES.toStringValue()).setButtons(buttons);
        bot.setOutput(user, message);
    }

    /**
     * Меняет блюда, которые не переносит пользователь
     */
    private void changeIntolerances(User user, String intolerance)
    {
        userService.addIntolerance(user, intolerance);
        Message message = new Message(intolerance + CommandsOutput.ADD_INTOLERANCES.toStringValue());
        bot.setOutput(user, message);
    }

    /**
     * Удаляет блюда, которые не переносит пользователь
     */
    private void removeIntolerances(User user)
    {
        userService.removeIntolerances(user);
        bot.setOutput(user, new Message(CommandsOutput.REMOVE_INTOLERANCES.toStringValue()));
    }

    /**
     * Пишет сообщение с кнопками, с помощью которых можно заполнить информацию о диете пользователя,
     */
    private void setDiet(User user)
    {
        Message message = new Message(CommandsOutput.CHOOSE_DIET.toStringValue()).setButtons(
                List.of(List.of(
                                new Button(Diets.KETOGENIC.toStringValue(),
                                        x -> changeDiet(user, Diets.KETOGENIC.toStringValue())),
                                new Button(Diets.VEGETARIAN.toStringValue(),
                                        x -> changeDiet(user, Diets.VEGETARIAN.toStringValue())),
                                new Button(Diets.LACTO_VEGETARIAN.toStringValue(),
                                        x -> changeDiet(user, Diets.LACTO_VEGETARIAN.toStringValue()))),
                        List.of(
                                new Button(Diets.OVO_VEGETARIAN.toStringValue(),
                                        x -> changeDiet(user, Diets.OVO_VEGETARIAN.toStringValue())),
                                new Button(Diets.VEGAN.toStringValue(),
                                        x -> changeDiet(user, Diets.VEGAN.toStringValue())),
                                new Button(Diets.PESCETARIAN.toStringValue(),
                                        x -> changeDiet(user, Diets.PESCETARIAN.toStringValue()))),
                        List.of(
                                new Button(Diets.LOW_FODMAP.toStringValue(),
                                        x -> changeDiet(user, Diets.LOW_FODMAP.toStringValue())),
                                new Button(Diets.WHOLE30.toStringValue(),
                                        x -> changeDiet(user, Diets.WHOLE30.toStringValue())),
                                new Button(Diets.PRIMAL.toStringValue(),
                                        x -> changeDiet(user, Diets.PRIMAL.toStringValue()))),
                        List.of(
                                new Button("Remove diet", x -> changeDiet(user, "")))));
        bot.setOutput(user, message);
    }

    /**
     * Меняет диету пользователя
     */
    private void changeDiet(User user, String diet)
    {
        userService.setDiet(user, diet);
        if (!diet.isEmpty())
            bot.setOutput(user, new Message(CommandsOutput.CHANGE_DIET.toStringValue()));
        else
            bot.setOutput(user, new Message(CommandsOutput.REMOVE_DIET.toStringValue()));
    }
}

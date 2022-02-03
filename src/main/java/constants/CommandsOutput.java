package constants;

/**
 * Вывод комманд.
 */
public enum CommandsOutput
{
    DISH_TITLE("Enter the name of the dish"),

    DISH_TITLE_TO_ADD("Enter the name of the dish you are adding"),

    DISH_TITLE_TO_REMOVE("Enter the name of the dish you want to remove"),

    DISH_REMOVED("The dish has been removed, congratulations"),

    NOT_ENOUGH_RIGHTS("You don't have enough rights"),

    INGREDIENTS("Enter ingredients you have with a comma-separated list without spaces"),

    INGREDIENTS_TO_ADD("Enter ingredients from which the dish will be cooked with a comma-separated list without spaces"),

    IMAGE_TO_ADD("Enter image url"),

    SUMMARY_TO_ADD("Enter summary"),

    DISH_ADDED("The dish has been added, I hope you're happy"),

    NOT_ENOUGH_INGREDIENTS("There are not enough ingredients :( "),

    ADMIN_MODE("Welcome to the admin zone"),

    USER_MODE("You're no longer in admin mode"),

    ALREADY_ADMIN("You're already admin"),

    ALREADY_USER("You're already in user mode"),

    SHOW_USER_PAGE("Here is your page with your diet and intolerances"),

    DISH_IS_NOT_FOUND("Unfortunately, the dish hasn't be found :("),

    HELP("That's what i can do\n" + Commands.DISH_BY_TITLE.toStringValue() + " - find the dish by title\n" +
            Commands.DISHES_BY_PRODUCTS.toStringValue() + " - dishes you can cook with ingredients you have\n" +
            Commands.SAVE_DISHES.toStringValue() + " - save dishes\n" +
            Commands.LOAD_DISHES.toStringValue() + " - load dishes\n" +
            Commands.USER_MODE.toStringValue() + " - switch to user mode\n" +
            Commands.ADMIN_MODE.toStringValue() + " - switch to admin mode\n" +
            Commands.ADD_DISH.toStringValue() + " - add dish\n" +
            Commands.REMOVE_DISH.toStringValue() + " - remove dish\n" +
            Commands.USER_PAGE.toStringValue() + " - Show user page"),

    START("The chef is here!\nText me help and a will tell you what i can do\n"),

    CHOOSE_DIET("Choose your diet (just one)"),

    CHOOSE_INTOLERANCES("Choose your intolerances"),

    ADD_INTOLERANCES(" added to your intolerances"),

    REMOVE_INTOLERANCES("All your intolerances have been removed"),

    CHANGE_DIET("You've changed your diet. I'm glad you take care of your health"),

    REMOVE_DIET("You've removed your diet. We all need a short break sometimes"),

    UNKNOWN_COMMAND("This is unknown command");

    private final String value;

    CommandsOutput(String value)
    {
        this.value = value;
    }

    public String toStringValue()
    {
        return value;
    }
}

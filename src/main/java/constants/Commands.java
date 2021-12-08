package constants;

/**
 * Константы в коммандах.
 */
public enum Commands {
    DISH_TITLE("Enter the name of the dish"),

    DISH_TITLE_TO_ADD("Enter the name of the dish you are adding"),

    DISH_TITLE_TO_REMOVE("Enter the name of the dish you want to remove"),

    DISH_REMOVED("The dish has been removed, congratulations"),

    NOT_ENOUGH_RIGHTS("You don't have enough rights"),

    INGREDIENTS("Enter ingredients you have with a comma-separated list without spaces"),

    INGREDIENTS_TO_ADD("Enter ingredients from which the dish will be cooked with a comma-separated list without spaces"),

    DISH_ADDED("The dish has been added, I hope you're happy"),

    NOT_ENOUGH_INGREDIENTS("There are not enough ingredients :( "),

    ADMIN_MODE("Welcome to the admin zone"),

    USER_MODE("You're no longer in admin mode"),

    ALREADY_ADMIN("You're already admin"),

    ALREADY_USER("You're already in user mode"),

    DISH_IS_NOT_FOUND("Unfortunately, the dish hasn't be found :("),

    HELP("That's what i can do\n" +
            "/dish_by_title - find the dish by title\n" +
            "/dishes_by_products - dishes you can cook with ingredients you have\n" +
            "/save_dishes - save dishes\n" +
            "/load_dishes - load dishes\n"),

    START("The chef is here!\nText me /help and a will tell you what i can do\n"),

    UNKNOWN_COMMAND("This is unknown command"),

    CAN_BE_COOKED("You can cook:\n");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}

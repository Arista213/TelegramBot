package constants;

/**
 * Пользовательские команды.
 */
public enum Commands {
    START("/start"),
    HELP("help"),
    DISH_BY_TITLE("dish by title"),
    DISHES_BY_PRODUCTS("dishes by products"),
    SAVE_DISHES("save dishes"),
    LOAD_DISHES("load dishes"),
    USER_MODE("user mode"),
    ADMIN_MODE("admin mode"),
    ADD_DISH("add dish"),
    REMOVE_DISH("remove dish");


    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}

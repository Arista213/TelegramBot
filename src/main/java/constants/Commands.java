package constants;

/**
 * Пользовательские команды.
 */
public enum Commands
{
    START("/start"),
    HELP("Help"),
    DISH_BY_TITLE("Dish by title"),
    DISHES_BY_PRODUCTS("Dishes by products"),
    SAVE_DISHES("Save dishes"),
    LOAD_DISHES("Load dishes"),
    USER_MODE("User mode"),
    ADMIN_MODE("Admin mode"),
    ADD_DISH("Add dish"),
    REMOVE_DISH("Remove dish");

    private final String value;

    Commands(String value)
    {
        this.value = value;
    }

    public String toStringValue()
    {
        return value;
    }

    public String toLowerCaseValue()
    {
        return value.toLowerCase();
    }
}

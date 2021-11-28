package constants;

/**
 * Константы в программе.
 */
public enum Config {
    BOT_NAME("Шеф-повар"),
    BOT_TOKEN("1881683881:AAFpOdIJPVsY0jvIYE7QkjEHc2jq0AL3rA8"),
    JSON_DISHES_PATH("target" + System.getProperty("file.separator") + "dishes.json");

    private final String value;

    Config(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}

package constants;

/**
 * Константы в программе.
 */
public enum Config {
    BOT_NAME("Шеф-повар"),
    BOT_TOKEN(System.getenv("TOKEN")),
    JSON_DISHES_PATH("target" + System.getProperty("file.separator") + "dishes.json");

    private final String value;

    Config(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
    }

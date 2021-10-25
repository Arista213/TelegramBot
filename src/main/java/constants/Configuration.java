package constants;

/**
 * Константы в программе.
 */
public enum Configuration {
    BOT_NAME("Шеф-повар"),
    BOT_TOKEN("#####################");

    private final String value;

    Configuration(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}

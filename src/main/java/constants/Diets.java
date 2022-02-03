package constants;

/**
 * Перечесление диет
 */
public enum Diets
{
    KETOGENIC("Ketogenic"),
    VEGETARIAN("Vegetarian"),
    LACTO_VEGETARIAN("Lacto-Vegetarian"),
    OVO_VEGETARIAN("Ovo-Vegetarian"),
    VEGAN("Vegan"),
    PESCETARIAN("Pescetarian"),
    LOW_FODMAP("Low FODMAP"),
    WHOLE30("Whole30"),
    PRIMAL("Primal");

    private final String value;

    Diets(String value)
    {
        this.value = value;
    }

    public String toStringValue()
    {
        return value;
    }
}

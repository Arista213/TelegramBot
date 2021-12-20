package constants;

/**
 *  Перечисление продуктов, которые пользователь может не переносить
 */
public enum Intolerances
{

    DAIRY("Dairy"),
    EGG("Egg"),
    GLUTEN("Gluten"),
    SEAFOOD("Seafood"),
    SESAME("Sesame"),
    SHELLFISH("Shellfish"),
    TREE_NUT("Tree nut"),
    WHEAT("Wheat"),
    SOY("Soy"),
    SULFATE("Sulfite"),
    GRAIN("Grain"),
    PEANUT("Peanut");
    private final String value;

    Intolerances(String value)
    {
        this.value = value;
    }

    public String toStringValue()
    {
        return value;
    }
}

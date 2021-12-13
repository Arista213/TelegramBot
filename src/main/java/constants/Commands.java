package constants;

/**
 * Константы в коммандах.
 */
public enum Commands {
    DISH_TITLE("Введите название блюда, которое вы хотите приготовить"),

    DISH_TITLE_TO_ADD("Введите название блюда, которое вы добавляете"),

    DISH_TITLE_TO_REMOVE("Введите название блюда, которое вы хотите удалить"),

    DISH_REMOVED("Блюдо удалено"),

    NOT_ENOUGH_RIGHTS("У вас недостаточно прав"),

    INGREDIENTS("Введите ингредиенты, которые у вас имеются через запятую без пробела"),

    INGREDIENTS_TO_ADD("Введите ингредиенты, из которых будет приготовлено блюдо через запятую без пробела"),

    DISH_ADDED("Блюдо добавлено, надеюсь вы счастливы"),

    NOT_ENOUGH_INGREDIENTS("Сходи в магазин("),

    ADMIN_MODE("Теперь вы в режиме администратора"),

    USER_MODE("Вы больше не в режиме администратора"),

    ALREADY_ADMIN("Вы уже админ"),

    ALREADY_USER("Вы уже в пользовательском режиме"),

    DISH_IS_NOT_FOUND("К сожалению блюдо не найдено("),

    HELP("Вот то, что я умею делать\n" +
            "/dish_by_title - рецепт блюда по его названию\n" +
            "/dishes_by_products - рецепт блюд, которые можно приготовить при имеющихся ингредиентах\n" +
            "/save_dishes - сохранить блюда\n" +
            "/load_dishes - загрузить блюда\n"),

    START("Шеф-повар здесь!\nНапиши мне /help и я расскажу, что умею\n"),

    UNKNOWN_COMMAND("Неизвестная комманда");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}

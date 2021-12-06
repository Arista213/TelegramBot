package constants;

/**
 * Вывод комманд.
 */
public enum CommandsOutput {
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
            Commands.DISH_BY_TITLE.toStringValue() + " - рецепт блюда по его названию\n" +
            Commands.DISHES_BY_PRODUCTS.toStringValue() + " - рецепт блюд, которые можно приготовить при имеющихся ингредиентах\n" +
            Commands.SAVE_DISHES.toStringValue() + " - сохранить блюда\n" +
            Commands.LOAD_DISHES.toStringValue() + " - загрузить блюда\n" +
            Commands.USER_MODE.toStringValue() + " - переключиться в режим пользователя\n" +
            Commands.ADMIN_MODE.toStringValue() + " - переключиться в режим администратора\n" +
            Commands.ADD_DISH.toStringValue() + " - добавить блюло\n" +
            Commands.REMOVE_DISH.toStringValue() + " - удалить блюдо\n"),

    START("Шеф-повар здесь!\nНапиши мне " + Commands.HELP.toStringValue() + " и я расскажу, что умею\n"),

    UNKNOWN_COMMAND("Неизвестная комманда"),

    CAN_BE_COOKED("Можно приготовить:\n");

    private final String value;

    CommandsOutput(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}

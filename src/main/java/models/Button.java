package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Контейнер для телеграм кнопки.
 */
public class Button {
    /**
     * Текст на кнопке.
     */
    private final String text;

    /**
     * Функция, которая будет выполнена, когда пользователь нажмет на эту кнопку.
     */
    private final ICallback callback;

    public Button(String text, ICallback action) {
        this.text = text;
        this.callback = action;
    }

    /**
     * @param buttonsMap Словарь названий кнопок и их методов.
     * @return Список кнопок.
     */
    public static List<Button> createButtons(Map<String, ICallback> buttonsMap) {
        List<Button> buttons = new ArrayList<>();
        buttonsMap.forEach((title, action) -> buttons.add(new Button(title, action)));
        return buttons;
    }

    public ICallback getCallback() {
        return callback;
    }

    public String getText() {
        return text;
    }
}

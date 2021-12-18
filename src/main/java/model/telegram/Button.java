package model.telegram;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Контейнер для телеграм кнопки.
 */
public class Button
{
    private final String title;
    private final ICallback callback;

    public Button(String title, ICallback action)
    {
        this.title = title;
        this.callback = action;
    }

    /**
     * @param buttonsMap Словарь названий кнопок и их методов.
     * @return Список кнопок.
     */
    public static List<Button> createButtons(Map<String, ICallback> buttonsMap)
    {
        List<Button> buttons = new ArrayList<>();
        buttonsMap.forEach((title, action) -> buttons.add(new Button(title, action)));
        return buttons;
    }

    public ICallback getCallback()
    {
        return callback;
    }

    public String getTitle()
    {
        return title;
    }
}

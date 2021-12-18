package model;

import model.telegram.Button;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Сущность сообщения для взаимодействия с пользователем.
 */
public class Message
{
    private final String text;
    private URL image;
    private List<Button> buttons;

    public Message(String text)
    {
        this.text = text;
    }


    public String getText()
    {
        return text;
    }

    public URL getImageURL()
    {
        return image;
    }

    public void setImageURL(String url)
    {
        try
        {
            this.image = new URL(url);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Button> getButtons()
    {
        return buttons;
    }

    public void setButtons(List<Button> buttons)
    {
        this.buttons = buttons;
    }
}

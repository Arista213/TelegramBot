package message.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Сущность сообщения для взаимодействия с пользователем.
 */
public class Message
{
    private final String text;
    private URL image;

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
}

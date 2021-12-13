import message.MessageWaiter;
import message.model.IAction;
import message.model.Message;
import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для MessageWaiter
 */
public class MessageWaiterTests
{
    String output;

    /**
     * Тест на то, что после добавления действия в MessageWaiter, он будет ждать сообщение.
     */
    @Test
    void messageWaitingTest()
    {
        MessageWaiter messageWaiter = new MessageWaiter();
        IAction testAction = (user, message) ->
        {
        };
        messageWaiter.add(testAction);
        assertTrue(messageWaiter.isWaiting());
    }

    /**
     * Тест на выполнение действия после получения сообщения.
     */
    @Test
    void messageWaiterExecuteTest()
    {
        MessageWaiter messageWaiter = new MessageWaiter();
        IAction testAction = (user, message) -> output = message.getText();
        messageWaiter.add(testAction);
        messageWaiter.execute(new User(0L), new Message("Test!"));
        assertEquals("Test!", output);
    }
}

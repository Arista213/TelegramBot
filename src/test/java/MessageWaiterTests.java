import IO.waiter.MessageWaiter;
import model.Message;
import model.User;
import model.IAction;
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
        MessageWaiter messageWaiter = new MessageWaiter(new User(0L));
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
        MessageWaiter messageWaiter = new MessageWaiter(new User(0L));
        IAction testAction = (user, message) -> output = message.getText();
        messageWaiter.add(testAction);
        messageWaiter.execute(new Message("Test!"));
        assertEquals("Test!", output);
    }
}

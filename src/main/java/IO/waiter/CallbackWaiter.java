package IO.waiter;

import model.telegram.ICallback;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class CallbackWaiter
{
    private final Map<String, ICallback> callbacks = new HashMap<>();
    private final User user;
    /**
     * Для каждой кнопки устанавливать свой идентификатор.
     */
    private Integer id = 0;

    public CallbackWaiter(User user)
    {
        this.user = user;
    }

    public Integer add(ICallback callback)
    {
        id++;
        callbacks.put(id.toString(), callback);
        return id;
    }

    public void execute(String callData)
    {
        callbacks.get(callData).execute(user);
    }
}

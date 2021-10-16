import logic.Bot;
import logic.communication.ICommunicateService;
import logic.communication.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Тестоый сервис для проверки функциональности бота.
 */
public class TestCommunicateService implements ICommunicateService {
    private final Queue<String> instructionSet = new LinkedList<>();
    private final Queue<String> outputQueue = new LinkedList<>();

    /**
     * @param instructionSet набор инструкций, который будет введён пользователем.
     */
    public void setInstruction(List<String> instructionSet) {
        this.instructionSet.addAll(instructionSet);
    }

    @Override
    public Message getMessage() {
        return new Message(instructionSet.poll());
    }

    @Override
    public void sendMessage(Message message) {
        outputQueue.add(message.text);
    }

    public String getOutput() {
        return outputQueue.poll();
    }

    public void runInstruction(Bot bot) {
        while (!instructionSet.isEmpty()) {
            bot.runCommand(instructionSet.poll());
        }
    }
}

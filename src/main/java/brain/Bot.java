package brain;

import java.util.*;

public class Bot {
    private Map<String, Command> commands;

    private String message = null;
    private String output = null;

    public boolean commandInRunning;

    public Bot() {
        initCommands();

        Thread thread = new Thread(this::startListening);
        thread.start();
    }

    /**
     * Здесь происходит заполение словоря коммандами
     */
    private void initCommands() {
        commands = new HashMap<>();
        commands.put("/start", new Start());
        commands.put("/hello", new Hello());
        commands.put("/recipe", new RecipeByName());
    }

    private void startListening() {
        while (!Thread.currentThread().isInterrupted()) {
            listeningProcess();
        }
    }

    private void listeningProcess() {
        if (!commandInRunning) {
            if (message == null) return;
            runCommand();
        }
    }

    public void receive(String message) {
        this.message = message;
    }

    private void runCommand() {
        if (!commands.containsKey(message)) {
            setOutput("Unknown command!");
            message = null;
            return;
        }

        var c = commands.get(message);
        Thread thread = new Thread(() -> c.process(this));
        thread.start();
        message = null;
    }

    public synchronized String getMessage() {
        String tempMessage = message;
        message = null;
        return tempMessage;
    }

    public synchronized String getOutput() {
        String tempOutput = output;
        output = null;
        return tempOutput;
    }

    public synchronized void setOutput(String output) {
        this.output = output;
    }
}

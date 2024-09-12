import java.util.Objects;

public class TaskCLI {
    public TaskCLI() {
    }

    public String[] processInputIntoArray(String input) {
        return input.split(" ");
    }

    public String getActionType(String[] cliArgs) throws Exception {
        if (cliArgs.length < 2) {
            throw new Exception("Insufficient args supplied");
        }
        if (!Objects.equals(cliArgs[0], "task-cli")) {
            throw new Exception("Invalid command, try starting with 'task-cli'.");
        }
        return cliArgs[1];
    }
}

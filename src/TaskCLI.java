import com.google.gson.JsonArray;

import java.util.Objects;

public class TaskCLI {

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

    public void add(Task newTask) {
        FileManager fileManager = new FileManager();
        String pathname = fileManager.getJSONFile();
        JsonArray jsonArray = fileManager.readFromJSONFile(pathname);
        fileManager.writeToJSONFile(pathname, jsonArray, newTask);
    }
}

import com.google.gson.JsonArray;

import java.util.Objects;

public class TaskCLI {

    public String[] processInputIntoArray(String input) {
        String regex = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        //String regex = " ";
        return input.split(regex);

    }

    public ActionType getActionType(String[] cliArgs) throws Exception {
        if (cliArgs.length < 2) {
            throw new Exception("Insufficient args supplied");
        }
        if (!Objects.equals(cliArgs[0], "task-cli")) {
            throw new Exception("Invalid command, try starting with 'task-cli'.");
        }
        return ActionType.valueOf(cliArgs[1].toUpperCase());
    }

    public void add(Task newTask) {
        FileManager fileManager = new FileManager();
        String pathname = fileManager.getJSONFile();
        JsonArray jsonArray = fileManager.readFromJSONFile(pathname);
        fileManager.writeToJSONFile(pathname, jsonArray, newTask);
    }

    public JsonArray list() {
        FileManager fileManager = new FileManager();
        String pathname = fileManager.getJSONFile();
        return fileManager.readFromJSONFile(pathname);
    }
}
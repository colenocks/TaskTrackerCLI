import com.google.gson.JsonArray;

import java.util.List;
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
        return ActionType.valueOf(cliArgs[1].toLowerCase());
    }

    public void add(FileManager fileManager, Task newTask) {
        JsonArray jsonArray = fileManager.readFromJSONFile();
        fileManager.addTaskToJSONFile(jsonArray, newTask);
    }

    public JsonArray list(FileManager fileManager) {
        return fileManager.readFromJSONFile();
    }

    public List<Task> listByStatus(FileManager fileManager, StatusType status) {
        JsonArray jsonArray = fileManager.readFromJSONFile();
        List<Task> list = fileManager.getJSONAsArrayList(jsonArray);
        return list.stream().filter(t -> t.getStatus().equals(status)).toList();
    }

    public void delete(FileManager fileManager, String id) {
        JsonArray jsonArray = fileManager.readFromJSONFile();
        fileManager.deleteObjectFromJSON(jsonArray, id);
    }

    public void update(FileManager fileManager, String id, String description) {
        JsonArray jsonArray = fileManager.readFromJSONFile();
        fileManager.updateObjectFromJSON(jsonArray, id, description);
    }
}
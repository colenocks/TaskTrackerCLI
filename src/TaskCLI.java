import com.google.gson.JsonArray;

import java.util.List;
import java.util.Objects;

public class TaskCLI {

    public String[] processInputIntoArray(String input) {
        String regex = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        //String regex = " ";
        return input.split(regex);

    }

    private static String parseString(String string) {
        return string.replaceAll("-", "_").toLowerCase();
    }

    public ActionType validateCLIArgs(String[] cliArgs) {
        if (cliArgs.length < 2) {
            throw new ArrayIndexOutOfBoundsException("\nMessage: Insufficient args supplied");
        }
        if (!Objects.equals(cliArgs[0], "task-cli")) {
            throw new IllegalArgumentException("\nMessage: Invalid command, try starting with 'task-cli'.");
        }

        String action = parseString(cliArgs[1]);
        if (!CheckEnum.isActionType(action)) {
            throw new IllegalArgumentException("\nMessage: Action type is invalid. Run 'task-cli help' for guide");
        }

        switch (ActionType.valueOf(action)) {
            case add:
                // when action type is add, description must come next.
                if (cliArgs.length < 3) {
                    throw new ArrayIndexOutOfBoundsException("\nMessage: Cannot add an empty task. " +
                            "Run 'task-cli help' for guide.");
                }
            case list:
                // when action type is list, status comes next (otherwise list all)
                if (cliArgs.length == 2) break;
                String status = parseString(cliArgs[2]);
                if (!CheckEnum.isStatusType(status)) {
                    throw new IllegalArgumentException("\nMessage: Status is invalid. Valid statuses are " +
                            "'done', 'todo' & 'in-progress'.");
                }
            case update, delete, mark_done, mark_in_progress:
                // when deleting or updating description/status
                if (Task.isValidId(cliArgs[2])) {
                    System.out.println("\nMessage: Id is invalid.");
                }
        }

        // TODO: return a hashMap of arguments instead
        return ActionType.valueOf(action);
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

    public void updateDescription(FileManager fileManager, String id, String description) {
        JsonArray jsonArray = fileManager.readFromJSONFile();
        fileManager.updateObjectFromJSON(jsonArray, id, description, null);
    }

    public void updateStatus(FileManager fileManager, String id, StatusType status) {
        JsonArray jsonArray = fileManager.readFromJSONFile();
        fileManager.updateObjectFromJSON(jsonArray, id, null, status);
    }
}
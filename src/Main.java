import com.google.gson.JsonArray;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String inputString = getInputFromUser();

        TaskCLI taskCLI = new TaskCLI();
        String[] cliArgs = taskCLI.processInputIntoArray(inputString);

        ActionType actionType;
        try {
            actionType = taskCLI.getActionType(cliArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String filepath = "tasks.json";
        FileManager fileManager = new FileManager(filepath);
        fileManager.createJSONFile();

        int totalTasks = 0;

        if (actionType.equals(ActionType.ADD)) {
            String description = cliArgs[2].replaceAll("^([\"'])|('|\"$)", "");

            // TODO: validate arg
            Task task = new Task(++totalTasks, description);
            taskCLI.add(fileManager, task);
        } else if (actionType.equals(ActionType.LIST)) {
            JsonArray json = taskCLI.list(fileManager);
            System.out.println(json);
        } else if (actionType.equals(ActionType.DELETE)) {
            String id = cliArgs[2];
            taskCLI.delete(fileManager, id);
        }
    }

    private static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter prompt: e.g task-cli add \"Buy groceries\"");
        // TODO: print a much verbose and detailed guide on how to use.
        return scanner.nextLine();
    }
}
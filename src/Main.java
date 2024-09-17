import java.util.List;
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

        // TODO: validate args
        switch (actionType) {
            case add -> {
                final String description = removeSurroundingQuotes(cliArgs[2]);
                Task task = new Task(String.valueOf(++totalTasks), description);
                taskCLI.add(fileManager, task);
            }
            case list -> {
                // # Listing tasks by status
                if (cliArgs.length > 2) {
                    List<Task> filteredList = taskCLI.listByStatus(fileManager, StatusType.valueOf(cliArgs[2]));
                    System.out.println(filteredList);
                    return;
                }
                // # Listing all tasks
                System.out.println(taskCLI.list(fileManager));
            }
            case update -> {
                String id = cliArgs[2];
                String description = removeSurroundingQuotes(cliArgs[3]);
                taskCLI.update(fileManager, id, description);
            }
            case delete -> {
                String id = cliArgs[2];
                taskCLI.delete(fileManager, id);
            }
        }
    }

    private static String removeSurroundingQuotes(String string) {
        return string.replaceAll("^([\"'])|('|\"$)", "");
    }

    private static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter prompt: e.g task-cli add \"Buy groceries\"");
        // TODO: print a much verbose and detailed guide on how to use.
        return scanner.nextLine();
    }
}
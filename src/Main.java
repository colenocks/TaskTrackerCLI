import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(CLIColors.PURPLE_BACKGROUND_BRIGHT +
                CLIColors.WHITE_BOLD_BRIGHT +
                "\tTask Tracker CLI 2024\t" +
                CLIColors.RESET);

        final String inputString = getInputFromUser();

        TaskCLI taskCLI = new TaskCLI();
        String[] cliArgs = taskCLI.processInputIntoArray(inputString);

        ActionEnum action;
        try {
            action = taskCLI.validateCLIArgs(cliArgs);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            System.out.println("Please run command again.");
            return;
        }

        String filepath = "tasks.json";
        FileManager fileManager = new FileManager(filepath);
        fileManager.createJSONFile();

        int totalTasks = 0;

        switch (action) {
            case help -> Helper.printHelpText();
            case add -> {
                final String description = Helper.removeSurroundingQuotes(cliArgs[2]);
                Task task = new Task(String.valueOf(++totalTasks), description);
                taskCLI.add(fileManager, task);
            }
            case list -> {
                // # Listing tasks by status
                if (cliArgs.length > 2) {
                    List<Task> filteredList = taskCLI.listByStatus(fileManager, StatusEnum.valueOf(cliArgs[2]));
                    System.out.println(filteredList);
                    return;
                }
                // # Listing all tasks
                System.out.println(taskCLI.list(fileManager));
            }
            case update -> {
                String id = cliArgs[2];
                String description = Helper.removeSurroundingQuotes(cliArgs[3]);
                taskCLI.updateDescription(fileManager, id, description);
            }
            case delete -> {
                String id = cliArgs[2];
                taskCLI.delete(fileManager, id);
            }
            case mark_done -> {
                String id = cliArgs[2];
                StatusEnum status = StatusEnum.done;
                taskCLI.updateStatus(fileManager, id, status);
            }
            case mark_in_progress -> {
                String id = cliArgs[2];
                StatusEnum status = StatusEnum.in_progress;
                taskCLI.updateStatus(fileManager, id, status);
            }
        }
    }

    private static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter prompt: e.g task-cli add \"Buy groceries\"");
        // TODO: print a much verbose and detailed guide on how to use.
        return scanner.nextLine();
    }
}
import com.google.gson.JsonArray;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String inputString = getInputFromUser();

        TaskCLI taskCLI = new TaskCLI();
        ActionType actionType;
        String[] cliArgs = taskCLI.processInputIntoArray(inputString);

        try {
            actionType = taskCLI.getActionType(cliArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (actionType.equals(ActionType.ADD)) {
            String description = cliArgs[2].replaceAll("^([\"'])|('|\"$)", "");

            // TODO: validate arg
            Task task = new Task(description);
            taskCLI.add(task);
        } else if (actionType.equals(ActionType.LIST)) {
            JsonArray json = taskCLI.list();
            System.out.println(json);
        }
    }

    private static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter prompt: e.g task-cli add \"Buy groceries\"");
        // TODO: print a much verbose and detailed guide on how to use.
        return scanner.nextLine();
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String inputString = getInputFromUser();

        TaskCLI taskCLI = new TaskCLI();
        String actionType;

        String[] cliArgs = taskCLI.processInputIntoArray(inputString);
        try {
            actionType = taskCLI.getActionType(cliArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Action type = " + actionType);
        Task task = new Task("Buy Groceries");

        if (actionType.equals("add")) {
            taskCLI.add(task);
        }
    }

    private static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter prompt: e.g task-cli add \"Buy groceries\"");
        // TODO: print a much verbose and detailed guide on how to use.
        return scanner.nextLine();
    }
}
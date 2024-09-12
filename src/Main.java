import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println(inputString);

        TaskCLI taskCLI = new TaskCLI();
        String[] cliArgs = taskCLI.processInputIntoArray(inputString);
        String actionType;
        try {
            actionType = taskCLI.getActionType(cliArgs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Action type = " + actionType);
        Task task = new Task("Bye bye");
        Task task2 = new Task("Buy Groceries");

        System.out.println(task);
        System.out.println(task2);
    }
}
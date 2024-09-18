import java.util.Arrays;

public class Helper {
    public static boolean isActionType(String string) {
        return Arrays.stream(ActionEnum.values()).anyMatch(e -> e.name().equals(string));
    }

    public static boolean isStatusType(String string) {
        return Arrays.stream(StatusEnum.values()).anyMatch(e -> e.name().equals(string));
    }

    public static String removeSurroundingQuotes(String string) {
        return string.replaceAll("^([\"'])|('|\"$)", "");
    }

    public static void printHelpText() {
        System.out.println(CLIColors.CYAN + "# Adding a new task" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.println(CLIColors.YELLOW_BOLD_BRIGHT + "add \"Buy groceries\"" + CLIColors.RESET);
        System.out.println();

        System.out.println(CLIColors.CYAN + "# Updating and deleting tasks" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "update " + CLIColors.RESET);
        System.out.print(CLIColors.PURPLE_BOLD_BRIGHT + "1 " + CLIColors.RESET);
        System.out.println(CLIColors.YELLOW_BOLD_BRIGHT + "\"Buy groceries and cook dinner\"" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "delete " + CLIColors.RESET);
        System.out.println(CLIColors.PURPLE_BOLD_BRIGHT + "1" + CLIColors.RESET);
        System.out.println();

        System.out.println(CLIColors.CYAN + "# Marking a task as in progress or done" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "mark-in-progress " + CLIColors.RESET);
        System.out.println(CLIColors.PURPLE_BOLD_BRIGHT + "1" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "mark-done " + CLIColors.RESET);
        System.out.println(CLIColors.PURPLE_BOLD_BRIGHT + "1" + CLIColors.RESET);
        System.out.println();

        System.out.println(CLIColors.CYAN + "# Listing all tasks" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.println(CLIColors.YELLOW_BOLD_BRIGHT + "list" + CLIColors.RESET);
        System.out.println();

        System.out.println(CLIColors.CYAN + "# Listing tasks by status" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "list " + CLIColors.RESET);
        System.out.println(CLIColors.YELLOW_BOLD_BRIGHT + "done" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "list " + CLIColors.RESET);
        System.out.println(CLIColors.YELLOW_BOLD_BRIGHT + "todo" + CLIColors.RESET);
        System.out.print(CLIColors.GREEN_BOLD_BRIGHT + "task-cli " + CLIColors.RESET);
        System.out.print(CLIColors.YELLOW_BOLD_BRIGHT + "list " + CLIColors.RESET);
        System.out.println(CLIColors.YELLOW_BOLD_BRIGHT + "in-progress" + CLIColors.RESET);
        System.out.println();
    }
}

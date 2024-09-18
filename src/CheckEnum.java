import java.util.Arrays;

public class CheckEnum {
    public static boolean isActionType(String string) {
        return Arrays.stream(ActionType.values()).anyMatch(e -> e.name().equals(string));
    }

    public static boolean isStatusType(String string) {
        return Arrays.stream(StatusType.values()).anyMatch(e -> e.name().equals(string));
    }
}

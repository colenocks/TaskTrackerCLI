import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private final String id;
    final String createdAt;
    private String description;
    private StatusEnum status;
    private String updatedAt;

    public Task(String id, String description) {
        this.id = id;
        this.status = StatusEnum.todo;
        this.description = description;

        final String currentDate = getCurrentDate();
        this.createdAt = currentDate;
        this.updatedAt = currentDate;
    }

    private static String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormatter.format(date);
    }

    public static boolean isValidId(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "{\n" +
                "id:" + id + ",\n" +
                "description: " + description + ",\n" +
                "status: " + status + ",\n" +
                "createdAt: " + createdAt + ",\n" +
                "updatedAt:" + updatedAt +
                "\n}";
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdatedAt() {
        this.updatedAt = getCurrentDate();
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}

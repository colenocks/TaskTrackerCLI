import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private final String id;
    private final String description;
    private final String status;
    private final String createdAt;
    private final String updatedAt;

    private static int total = 0;

    public Task(String description) {
        this.id = String.valueOf(total++);
        this.status = "todo";
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

    @Override
    public String toString() {
        return id + ", " + description + ", " + status + ", " + createdAt + ", " + updatedAt;
    }
}

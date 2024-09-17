import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
    static int total = 0;
    final String id;
    final String createdAt;
    String description;
    String status;
    String updatedAt;

    public Task(int id, String description) {
        // this.id = String.valueOf(total++);
        this.id = String.valueOf(id);
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

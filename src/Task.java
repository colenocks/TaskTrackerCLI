import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
    static int total = 0;
    private final String id;
    final String createdAt;
    private String description;
    private String status;
    private String updatedAt;

    public Task(String id, String description) {
        // this.id = String.valueOf(total++);
        this.id = id;
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

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

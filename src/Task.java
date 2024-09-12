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
        this.id = String.valueOf(++total);
        this.status = "todo";
        this.description = description;

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDate = dateFormatter.format(date);
        this.createdAt = currentDate;
        this.updatedAt = currentDate;
    }

    @Override
    public String toString() {
        return id + ", " + description + ", " + status + ", " + createdAt + ", " + updatedAt;
    }
}

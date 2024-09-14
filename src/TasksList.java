import java.util.HashMap;
import java.util.Map;

public class TasksList {
    protected Map<String, Task[]> tasksList = new HashMap<>();

    public TasksList(Task[] list) {
        tasksList.put("tasks", list);
    }
}

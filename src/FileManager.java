import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class FileManager {
    private String filepath;

    public FileManager(String filepath) {
        this.filepath = filepath;
    }

    public void createJSONFile() {
        try {
            File fileManager = new File(this.filepath);
            boolean isCreated = fileManager.createNewFile();
            if (isCreated) {
                System.out.println("File created.");
                this.filepath = fileManager.getPath();
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating file.");
            // Best Practice: Call to 'printStackTrace()' should probably be replaced
            // with more robust logging.
        }
    }

    public JsonArray readFromJSONFile() {
        // Try with resources statement -> to help manage resource automatically.
        try (Reader reader = new FileReader(this.filepath, StandardCharsets.UTF_8)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonNull()) {
                return new JsonArray(0);
            }
            return jsonElement.getAsJsonObject().get("tasks").getAsJsonArray();

        } catch (IOException e) {
            System.out.println("Error reading file.");
            return null;
        }
    }

    public void addTaskToJSONFile(JsonArray jsonArray, Task newTask) {
        ArrayList<Task> existingTasks = getJSONAsArrayList(jsonArray);
        existingTasks.addLast(newTask); // append new task

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        writeToJSONFile(gson, existingTasks);
        System.out.println("Task added.");
    }

    public void deleteObjectFromJSON(JsonArray jsonArray, String id) {
        ArrayList<Task> existingTasks = getJSONAsArrayList(jsonArray);
        boolean isDeleted = existingTasks.removeIf(t -> Objects.equals(t.getId(), id));
        if (!isDeleted) {
            System.out.println("Task not found or already deleted.");
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        writeToJSONFile(gson, existingTasks);
        System.out.println("Task deleted.");
    }

    public void updateObjectFromJSON(JsonArray jsonArray, String id, String description, StatusType status) {
        ArrayList<Task> existingTasks = getJSONAsArrayList(jsonArray);
        existingTasks.stream().filter(t -> t.getId().equals(id))
                .findAny().ifPresent(t -> {
                    if (description != null) t.setDescription(description);
                    if (status != null) t.setStatus(status);
                    t.setUpdatedAt();
                });

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        writeToJSONFile(gson, existingTasks);
        System.out.println("Task updated.");
    }

    public ArrayList<Task> getJSONAsArrayList(JsonArray jsonArray) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Task> existingTasks = new ArrayList<>(0);

        if (!jsonArray.isEmpty()) {
            Type typeObject = new TypeToken<ArrayList<Task>>() {
            }.getType();
            existingTasks = gson.fromJson(jsonArray, typeObject);
        }
        return existingTasks;
    }

    private void writeToJSONFile(Gson gson, ArrayList<Task> newTasks) {
        try (FileWriter fileWriter = new FileWriter(this.filepath, StandardCharsets.UTF_8)) {
            Task[] tasks = newTasks.toArray(new Task[0]); // extract only "tasks"
            TasksList list = new TasksList(tasks);
            // list: { "tasksList": { "tasks": [] } }
            gson.toJson(list.tasksList, fileWriter);
        } catch (IOException e) {
            System.out.println("Error writing to JSON file.");
        }
    }
}

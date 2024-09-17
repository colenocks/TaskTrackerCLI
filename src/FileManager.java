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
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }

    public void addTaskToJSONFile(JsonArray jsonArray, Task newTask) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Task> existingTasks = getJSONAsArrayList(gson, jsonArray);
        existingTasks.addLast(newTask); // append new task
        writeToJSonFile(gson, existingTasks);
        System.out.println("Task added.");
    }

    private void writeToJSonFile(Gson gson, ArrayList<Task> newTasks) {
        try (FileWriter fileWriter = new FileWriter(this.filepath, StandardCharsets.UTF_8)) {
            Task[] tasks = newTasks.toArray(new Task[0]); // extract only "tasks"
            TasksList list = new TasksList(tasks);
            // list: { "tasksList": { "tasks": [] } }
            gson.toJson(list.tasksList, fileWriter);
        } catch (IOException e) {
            System.out.println("Error writing to JSON file.");
            e.printStackTrace();
        }
    }

    public void deleteObjectFromJSON(JsonArray jsonArray, String id) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Task> existingTasks = getJSONAsArrayList(gson, jsonArray);
        boolean isDeleted = existingTasks.removeIf(t -> Objects.equals(t.getId(), id));
        if (!isDeleted) {
            System.out.println("Task not found or already deleted.");
            return;
        }
        writeToJSonFile(gson, existingTasks);
        System.out.println("Task deleted.");
    }

    public void updateObjectFromJSON(JsonArray jsonArray, String id, String description) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Task> existingTasks = getJSONAsArrayList(gson, jsonArray);

        existingTasks.stream().filter(t -> t.getId().equals(id))
                .findAny().ifPresent(t -> {
                    t.setDescription(description);
                    t.setUpdatedAt();
                });

        writeToJSonFile(gson, existingTasks);
        System.out.println("Task updated.");
    }

    private ArrayList<Task> getJSONAsArrayList(Gson gson, JsonArray jsonArray) {
        ArrayList<Task> existingTasks = new ArrayList<>(0);

        if (!jsonArray.isEmpty()) {
            Type typeObject = new TypeToken<ArrayList<Task>>() {
            }.getType();
            existingTasks = gson.fromJson(jsonArray, typeObject);
        }
        return existingTasks;
    }
}

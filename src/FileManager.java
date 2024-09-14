import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {
    public String getJSONFile() {
        String filepath = "tasks.json";
        try {
            File fileManager = new File(filepath);
            boolean isCreated = fileManager.createNewFile();
            if (isCreated) {
                System.out.println("File created.");
                return fileManager.getPath();
            }
            return filepath;
        } catch (IOException e) {
            System.out.println("An error occurred creating file.");
            // Best Practice: Call to 'printStackTrace()' should probably be replaced
            // with more robust logging.
            e.printStackTrace();
            return null;
        }
    }

    public JsonArray readFromJSONFile(String filepath) {
        // Try with resources statement -> to help manage resource automatically.
        try (Reader reader = new FileReader(filepath, StandardCharsets.UTF_8)) {
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

    public void writeToJSONFile(String filename, JsonArray jsonArray, Task newTask) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Task> existingTasks = new ArrayList<>(0);

        if (!jsonArray.isEmpty()) {
            Type typeObject = new TypeToken<ArrayList<Task>>() {
            }.getType();
            existingTasks = gson.fromJson(jsonArray, typeObject);
        }
        existingTasks.addLast(newTask); // append new task

        try (FileWriter fileWriter = new FileWriter(filename, StandardCharsets.UTF_8)) {
            // task -> { "tasksList": { "tasks": [] } }
            Task[] tasks = existingTasks.toArray(new Task[0]); // extract only "tasks"
            TasksList list = new TasksList(tasks);
            System.out.println();

            gson.toJson(list.tasksList, fileWriter);
            System.out.println("New Task added.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }
}

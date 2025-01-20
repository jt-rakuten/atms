package com.taskmanager.util;

import com.taskmanager.model.Category;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileStorage {
    private static final String TASKS_FILE = "tasks.dat";
    private static final String USERS_FILE = "users.dat";
    private static final String CATEGORIES_FILE = "categories.dat";

    public static void saveTasks(List<Task> tasks) {
        saveToFile(tasks, TASKS_FILE);
    }

    public static List<Task> loadTasks() {
        return loadFromFile(TASKS_FILE);
    }

    public static void saveUsers(List<User> users) {
        saveToFile(users, USERS_FILE);
    }

    public static List<User> loadUsers() {
        return loadFromFile(USERS_FILE);
    }

    public static void saveCategories(List<Category> categories) {
        saveToFile(categories, CATEGORIES_FILE);
    }

    public static List<Category> loadCategories() {
        return loadFromFile(CATEGORIES_FILE);
    }

    private static <T> void saveToFile(List<T> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.util.FileStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing tasks.
 */
public class TaskService {
    private List<Task> tasks;

    public TaskService() {
        tasks = FileStorage.loadTasks();

        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Creates a new task and adds it to the list.
     *
     * @param task the task to be created
     * @return the created task
     */
    public Task createTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return an Optional containing the task if found, or an empty Optional if not found
     */
    public Optional<Task> getTaskById(UUID id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    /**
     * Updates an existing task.
     *
     * @param id the ID of the task to update
     * @param updatedTask the updated task
     * @return an Optional containing the updated task if the update was successful, or an empty Optional if not
     */
    public Optional<Task> updateTask(UUID id, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.set(i, updatedTask);
                return Optional.of(updatedTask);
            }
        }
        return Optional.empty();
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     * @return true if the task was successfully deleted, false otherwise
     */
    public boolean deleteTask(UUID id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public void saveAll() {
        FileStorage.saveTasks(tasks);
    }
}
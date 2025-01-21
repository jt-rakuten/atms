package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.util.ErrorHandler;
import com.taskmanager.util.FileStorage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskService {
    private List<Task> tasks;

    public TaskService() {
        tasks = FileStorage.loadTasks();

        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    private boolean validateTask(Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            ErrorHandler.showWarning("Invalid Task", "Task title cannot be empty.");
            return false;
        }
        if (task.getDueDate() == null) {
            ErrorHandler.showWarning("Invalid Task", "Task must have a due date.");
            return false;
        }
        if (task.getDueDate().isBefore(LocalDateTime.now())) {
            ErrorHandler.showWarning("Invalid Task", "Due date cannot be in the past.");
            return false;
        }
        return true;
    }

    public Task createTask(Task task) {
        if (validateTask(task)) {
            try {
                tasks.add(task);
                saveAll();
                return task;
            } catch (Exception e) {
                ErrorHandler.showError("Error Creating Task", "An error occurred while creating the task: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> getTaskById(UUID id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Optional<Task> updateTask(UUID id, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.set(i, updatedTask);
                return Optional.of(updatedTask);
            }
        }
        return Optional.empty();
    }

    public boolean deleteTask(UUID id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public void saveAll() {
        try {
            FileStorage.saveTasks(tasks);
        } catch (Exception e) {
            ErrorHandler.showError("Error Saving Tasks", "An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public List<Task> getFilteredTasks(Predicate<Task> filter) {
        return tasks.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public List<Task> getSortedTasks(Comparator<Task> comparator) {
        return tasks.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Map<TaskStatus, Long> getTaskStatusCounts() {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }
}
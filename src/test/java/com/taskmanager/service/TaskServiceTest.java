package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void createTask() {
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1));
        Task createdTask = taskService.createTask(task);
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
    }

    @Test
    void getFilteredTasks() {
        taskService.createTask(new Task("Task 1", "Description", LocalDateTime.now().plusDays(1)));
        Task completedTask = new Task("Task 2", "Description", LocalDateTime.now().plusDays(2));
        completedTask.setStatus(TaskStatus.COMPLETED);
        taskService.createTask(completedTask);

        List<Task> pendingTasks = taskService.getFilteredTasks(task -> task.getStatus() == TaskStatus.PENDING);
        assertEquals(1, pendingTasks.size());
        assertEquals("Task 1", pendingTasks.get(0).getTitle());
    }

    // Add more tests for other methods
}

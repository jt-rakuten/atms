package com.taskmanager.controller;

import com.taskmanager.model.Category;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController {
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private ListView<User> userListView;
    @FXML
    private ListView<Category> categoryListView;

    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private CategoryService categoryService = new CategoryService();

    @FXML
    private void initialize() {
        refreshLists();
    }

    private void refreshLists() {
        taskListView.getItems().setAll(taskService.getAllTasks());
        userListView.getItems().setAll(userService.getAllUsers());
        categoryListView.getItems().setAll(categoryService.getAllCategories());
    }

    @FXML
    private void addTask() {
        // TODO: Implement add task dialog
    }

    @FXML
    private void editTask() {
        // TODO: Implement edit task dialog
    }

    @FXML
    private void deleteTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.deleteTask(selectedTask.getId());
            refreshLists();
        }
    }

    @FXML
    private void addUser() {
        // TODO: Implement add user dialog
    }

    @FXML
    private void editUser() {
        // TODO: Implement edit user dialog
    }

    @FXML
    private void deleteUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getId());
            refreshLists();
        }
    }

    @FXML
    private void addCategory() {
        // TODO: Implement add category dialog
    }

    @FXML
    private void editCategory() {
        // TODO: Implement edit category dialog
    }

    @FXML
    private void deleteCategory() {
        Category selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            categoryService.deleteCategory(selectedCategory.id());
            refreshLists();
        }
    }
}

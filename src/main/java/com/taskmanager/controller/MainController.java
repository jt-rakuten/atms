package com.taskmanager.controller;

import com.taskmanager.model.Category;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import java.io.IOException;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        Task newTask = new Task("", "", LocalDateTime.now());
        if (showTaskDialog(newTask)) {
            taskService.createTask(newTask);
            refreshLists();
        }
    }

    @FXML
    private void editTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            if (showTaskDialog(selectedTask)) {
                taskService.updateTask(selectedTask.getId(), selectedTask);
                refreshLists();
            }
        } else {
            showAlert("No Task Selected", "Please select a task to edit.");
        }
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

    private boolean showTaskDialog(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/taskmanager/task-dialog.fxml"));
            Parent root = loader.load();

            TaskDialogController controller = loader.getController();
            controller.setTask(task);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(taskListView.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

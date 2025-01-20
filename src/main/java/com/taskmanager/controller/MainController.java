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
        User newUser = new User("", "", "");
        if (showUserDialog(newUser)) {
            userService.createUser(newUser);
            refreshLists();
        }
    }

    @FXML
    private void editUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            if (showUserDialog(selectedUser)) {
                userService.updateUser(selectedUser.getId(), selectedUser);
                refreshLists();
            }
        } else {
            showAlert("No User Selected", "Please select a user to edit.");
        }
    }

    @FXML
    private void deleteUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getId());
            refreshLists();
        }
    }

    private boolean showUserDialog(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/taskmanager/user-dialog.fxml"));
            Parent root = loader.load();

            UserDialogController controller = loader.getController();
            controller.setUser(user);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(userListView.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    private void addCategory() {
        Category newCategory = new Category(null, "", "");
        if (showCategoryDialog(newCategory)) {
            categoryService.createCategory(newCategory);
            refreshLists();
        }
    }

    @FXML
    private void editCategory() {
        Category selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            if (showCategoryDialog(selectedCategory)) {
                categoryService.updateCategory(selectedCategory.id(), selectedCategory);
                refreshLists();
            }
        } else {
            showAlert("No Category Selected", "Please select a category to edit.");
        }
    }

    private boolean showCategoryDialog(Category category) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/taskmanager/category-dialog.fxml"));
            Parent root = loader.load();

            CategoryDialogController controller = loader.getController();
            controller.setCategory(category);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Category");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(categoryListView.getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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

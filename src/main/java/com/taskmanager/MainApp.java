package com.taskmanager;

import com.taskmanager.controller.LoginController;
import com.taskmanager.controller.MainController;
import com.taskmanager.model.User;
import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private CategoryService categoryService = new CategoryService();

    @Override
    public void start(Stage primaryStage) throws IOException {
        User loggedInUser = showLoginDialog();
        if (loggedInUser != null) {
            showMainApplication(primaryStage, loggedInUser);
        } else {
            System.exit(0);
        }
    }

    private User showLoginDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-dialog.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setUserService(userService);

        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setScene(new Scene(root));
        loginStage.showAndWait();

        return controller.getLoggedInUser();
    }

    private void showMainApplication(Stage primaryStage, User loggedInUser) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setServices(taskService, userService, categoryService);
        controller.setLoggedInUser(loggedInUser);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Task Manager - " + loggedInUser.getUsername());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

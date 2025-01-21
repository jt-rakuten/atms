package com.taskmanager;

import com.taskmanager.service.CategoryService;
import com.taskmanager.service.TaskService;
import com.taskmanager.service.UserService;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private TaskService taskService = new TaskService();
    private UserService userService = new UserService();
    private CategoryService categoryService = new CategoryService();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("Advanced Task Manager");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            taskService.saveAll();
            userService.saveAll();
            categoryService.saveAll();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

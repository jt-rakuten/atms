package com.taskmanager.controller;

import com.taskmanager.model.User;
import com.taskmanager.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorMessage;

    private UserService userService;
    private User loggedInUser;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Optional<User> user = userService.authenticateUser(username, password);
        if (user.isPresent()) {
            loggedInUser = user.get();
            closeDialog();
        } else {
            errorMessage.setText("Invalid username or password");
        }
    }

    private void closeDialog() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}

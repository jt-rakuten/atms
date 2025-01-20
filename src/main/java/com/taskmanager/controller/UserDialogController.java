package com.taskmanager.controller;

import com.taskmanager.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserDialogController {
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private User user;
    private boolean saveClicked = false;

    public void setUser(User user) {
        this.user = user;
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        // Note: For security reasons, we don't set the password field
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            user.setUsername(usernameField.getText());
            user.setEmail(emailField.getText());
            if (!passwordField.getText().isEmpty()) {
                user.setPasswordHash(hashPassword(passwordField.getText()));
            }

            saveClicked = true;
            closeDialog();
        }
    }

    @FXML
    private void handleCancel() {
        closeDialog();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (usernameField.getText() == null || usernameField.getText().isEmpty()) {
            errorMessage += "Username cannot be empty.\n";
        }
        if (emailField.getText() == null || emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
            errorMessage += "Please enter a valid email address.\n";
        }
        if (user.getPasswordHash() == null && (passwordField.getText() == null || passwordField.getText().isEmpty())) {
            errorMessage += "Password cannot be empty for new users.\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private void closeDialog() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private String hashPassword(String password) {
        // TODO: Implement proper password hashing
        return password;
    }
}

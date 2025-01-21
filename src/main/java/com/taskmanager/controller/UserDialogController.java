package com.taskmanager.controller;

import com.taskmanager.model.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserDialogController extends BaseDialogController {
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private User user;

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
            showAlert("Invalid Fields", "Please correct invalid fields", errorMessage, Alert.AlertType.ERROR);
            return false;
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

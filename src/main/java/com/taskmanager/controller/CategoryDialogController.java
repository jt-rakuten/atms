package com.taskmanager.controller;

import com.taskmanager.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CategoryDialogController extends BaseDialogController {
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;

    private Category category;

    public void setCategory(Category category) {
        this.category = category;
        nameField.setText(category.name());
        descriptionField.setText(category.description());
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            category = new Category(category.id(), nameField.getText(), descriptionField.getText());
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
        if (nameField.getText() == null || nameField.getText().isEmpty()) {
            errorMessage += "Category name cannot be empty.\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert("Invalid Fields", "Please correct invalid fields", errorMessage, Alert.AlertType.ERROR);
            return false;
        }
    }

    public Category getCategory() {
        return category;
    }
}

package com.taskmanager.controller;

import com.taskmanager.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CategoryDialogController {
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;

    private Category category;
    private boolean saveClicked = false;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private void closeDialog() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public Category getCategory() {
        return category;
    }
}

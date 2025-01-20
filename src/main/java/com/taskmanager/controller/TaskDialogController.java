package com.taskmanager.controller;

import com.taskmanager.model.Category;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskDialogController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker dueDatePicker;
    @FXML private ComboBox<TaskStatus> statusComboBox;
    @FXML private ComboBox<Category> categoryComboBox;

    private Task task;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        statusComboBox.getItems().setAll(TaskStatus.values());
        // TODO: Populate categoryComboBox with actual categories
    }

    public void setTask(Task task) {
        this.task = task;
        titleField.setText(task.getTitle());
        descriptionField.setText(task.getDescription());
        dueDatePicker.setValue(task.getDueDate().toLocalDate());
        statusComboBox.setValue(task.getStatus());
        categoryComboBox.setValue(task.getCategory());
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            task.setTitle(titleField.getText());
            task.setDescription(descriptionField.getText());
            task.setDueDate(LocalDateTime.of(dueDatePicker.getValue(), LocalDateTime.now().toLocalTime()));
            task.setStatus(statusComboBox.getValue());
            task.setCategory(categoryComboBox.getValue());

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
        if (titleField.getText() == null || titleField.getText().isEmpty()) {
            errorMessage += "Title cannot be empty.\n";
        }
        if (dueDatePicker.getValue() == null) {
            errorMessage += "Due date must be selected.\n";
        }
        if (statusComboBox.getValue() == null) {
            errorMessage += "Status must be selected.\n";
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
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public Task getTask() {
        return task;
    }
}

package com.taskmanager.controller;

import com.taskmanager.model.Category;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.service.CategoryService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskDialogController extends BaseDialogController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker dueDatePicker;
    @FXML private ComboBox<TaskStatus> statusComboBox;
    @FXML private ComboBox<Category> categoryComboBox;

    private CategoryService categoryService;
    private Task task;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @FXML
    private void initialize() {
        statusComboBox.getItems().setAll(TaskStatus.values());
        if (categoryService != null) {
            categoryComboBox.getItems().setAll(categoryService.getAllCategories());
        }

        dueDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });
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
            showAlert("Invalid Fields", "Please correct invalid fields", errorMessage, Alert.AlertType.ERROR);
            return false;
        }
    }

    public Task getTask() {
        return task;
    }
}

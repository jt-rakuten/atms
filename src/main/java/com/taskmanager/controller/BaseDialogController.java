package com.taskmanager.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class BaseDialogController {
    protected boolean saveClicked = false;
    protected Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    protected void closeDialog() {
        if (dialogStage != null) {
            dialogStage.close();
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    protected void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

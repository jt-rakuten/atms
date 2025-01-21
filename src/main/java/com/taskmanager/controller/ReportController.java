package com.taskmanager.controller;

import com.taskmanager.model.TaskStatus;
import com.taskmanager.service.TaskService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.Map;

public class ReportController {
    @FXML private PieChart statusChart;

    private TaskService taskService;

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @FXML
    private void initialize() {
        updateChart();
    }

    private void updateChart() {
        Map<TaskStatus, Long> statusCounts = taskService.getTaskStatusCounts();
        statusChart.getData().clear();
        for (Map.Entry<TaskStatus, Long> entry : statusCounts.entrySet()) {
            statusChart.getData().add(new PieChart.Data(entry.getKey().toString(), entry.getValue()));
        }
    }
}

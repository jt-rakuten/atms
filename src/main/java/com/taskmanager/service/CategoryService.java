package com.taskmanager.service;

import com.taskmanager.model.Category;
import com.taskmanager.util.ErrorHandler;
import com.taskmanager.util.FileStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryService {
    private List<Category> categories = new ArrayList<>();

    public CategoryService () {
        categories = FileStorage.loadCategories();
        if (categories == null) {
            categories = new ArrayList<>();
        }
    }

    private boolean validateCategory(Category category) {
        if (category.name() == null || category.name().trim().isEmpty()) {
            ErrorHandler.showWarning("Invalid Category", "Category name cannot be empty.");
            return false;
        }
        return true;
    }

    public Category createCategory(Category category) {
        if (validateCategory(category)) {
            try {
                categories.add(category);
                saveAll();
                return category;
            } catch (Exception e) {
                ErrorHandler.showError("Error Creating Category", "An error occurred while creating the category: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    public Optional<Category> getCategoryById(UUID id) {
        return categories.stream()
                .filter(category -> category.id().equals(id))
                .findFirst();
    }

    public Optional<Category> updateCategory(UUID id, Category updatedCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).id().equals(id)) {
                categories.set(i, updatedCategory);
                return Optional.of(updatedCategory);
            }
        }
        return Optional.empty();
    }

    public boolean deleteCategory(UUID id) {
        return categories.removeIf(category -> category.id().equals(id));
    }

    public void saveAll() {
        try {
            FileStorage.saveCategories(categories);
        } catch (Exception e) {
            ErrorHandler.showError("Error Saving Categories", "An error occurred while saving categories: " + e.getMessage());
        }
    }
}
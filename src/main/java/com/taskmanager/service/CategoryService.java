package com.taskmanager.service;

import com.taskmanager.model.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing categories.
 */
public class CategoryService {
    private final List<Category> categories = new ArrayList<>();

    /**
     * Creates a new category and adds it to the list.
     *
     * @param category the category to be created
     * @return the created category
     */
    public Category createCategory(Category category) {
        categories.add(category);
        return category;
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the ID of the category to retrieve
     * @return an Optional containing the category if found, or an empty Optional if not found
     */
    public Optional<Category> getCategoryById(UUID id) {
        return categories.stream()
                .filter(category -> category.id().equals(id))
                .findFirst();
    }

    /**
     * Updates an existing category.
     *
     * @param id the ID of the category to update
     * @param updatedCategory the updated category
     * @return an Optional containing the updated category if the update was successful, or an empty Optional if not
     */
    public Optional<Category> updateCategory(UUID id, Category updatedCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).id().equals(id)) {
                categories.set(i, updatedCategory);
                return Optional.of(updatedCategory);
            }
        }
        return Optional.empty();
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the ID of the category to delete
     * @return true if the category was successfully deleted, false otherwise
     */
    public boolean deleteCategory(UUID id) {
        return categories.removeIf(category -> category.id().equals(id));
    }
}
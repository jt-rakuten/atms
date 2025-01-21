package com.taskmanager.service;

import com.taskmanager.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService();
    }

    @Test
    void createCategory() {
        Category category = new Category(null, "Test Category", "Test Description");
        Category createdCategory = categoryService.createCategory(category);
        assertNotNull(createdCategory);
        assertEquals("Test Category", createdCategory.name());
    }

    // Add more tests for other CategoryService methods
}
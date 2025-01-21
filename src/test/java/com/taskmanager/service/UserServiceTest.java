package com.taskmanager.service;

import com.taskmanager.model.Category;
import com.taskmanager.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void createUser() {
        User user = new User("testuser", "test@example.com", "password");
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
    }

    @Test
    void authenticateUser() {
        User user = new User("testuser", "test@example.com", "password");
        userService.createUser(user);

        assertTrue(userService.authenticateUser("testuser", "password").isPresent());
        assertFalse(userService.authenticateUser("testuser", "wrongpassword").isPresent());
    }

    // Add more tests for other UserService methods
}
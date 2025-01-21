package com.taskmanager.service;

import com.taskmanager.model.User;
import com.taskmanager.util.ErrorHandler;
import com.taskmanager.util.FileStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService () {
        users = FileStorage.loadUsers();
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    private boolean validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            ErrorHandler.showWarning("Invalid User", "User username cannot be empty.");
            return false;
        }
        if (user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty()) {
            ErrorHandler.showWarning("Invalid User", "User password cannot be empty.");
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            ErrorHandler.showWarning("Invalid User", "User email cannot be empty.");
            return false;
        }
        return true;
    }

    public User createUser(User user) {
        if (validateUser(user)) {
            try {
                users.add(user);
                saveAll();
                return user;
            } catch (Exception e) {
                ErrorHandler.showError("Error Creating User", "An error occurred while creating the user: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public Optional<User> getUserById(UUID id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public Optional<User> updateUser(UUID id, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, updatedUser);
                return Optional.of(updatedUser);
            }
        }
        return Optional.empty();
    }

    public boolean deleteUser(UUID id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    public void saveAll() {
        try {
            FileStorage.saveUsers(users);
        } catch (Exception e) {
            ErrorHandler.showError("Error Saving Users", "An error occurred while saving users: " + e.getMessage());
        }
    }
}
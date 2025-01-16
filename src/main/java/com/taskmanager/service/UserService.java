package com.taskmanager.service;

import com.taskmanager.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing users.
 */
public class UserService {
    private final List<User> users = new ArrayList<>();

    /**
     * Creates a new user and adds it to the list.
     *
     * @param user the user to be created
     * @return the created user
     */
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return an Optional containing the user if found, or an empty Optional if not found
     */
    public Optional<User> getUserById(UUID id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param updatedUser the updated user
     * @return an Optional containing the updated user if the update was successful, or an empty Optional if not
     */
    public Optional<User> updateUser(UUID id, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, updatedUser);
                return Optional.of(updatedUser);
            }
        }
        return Optional.empty();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was successfully deleted, false otherwise
     */
    public boolean deleteUser(UUID id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
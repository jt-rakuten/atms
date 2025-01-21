package com.taskmanager.model;

import java.util.UUID;

public record Category(UUID id, String name, String description) {
    public Category(String name, String description) {
        this(UUID.randomUUID(), name, description);
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
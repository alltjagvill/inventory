package com.example.inventory.Model;

import java.io.Serializable;

public class ListRoom implements Serializable {

    private String name;
    private String description;

    public ListRoom()
    {}

    public ListRoom(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.inventory.Model;

import java.io.Serializable;

public class ListRoom implements Serializable {

    private String name;
    private String description;
    private String roomID;

    public ListRoom()
    {}

    public ListRoom(String name, String description, String roomID) {
        this.name = name;
        this.description = description;
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRoomID() {return roomID;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoomID(String roomID) {this.roomID = roomID;}
}

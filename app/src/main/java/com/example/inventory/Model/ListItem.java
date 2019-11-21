package com.example.inventory.Model;

import java.io.Serializable;

public class ListItem implements Serializable {

    private String itemName;
    private String itemID;
    private  String roomID;
    private String roomName;
    private int itemCount;
    private int warningCount;
    private int criticalCount;

    public ListItem()
    {}

    public ListItem(String itemName, String itemID, String roomID, String roomName, int itemCount, int warningCount, int criticalCount)
    {
        this.itemName = itemName;
        this.itemID = itemID;
        this.roomID = roomID;
        this.roomName = roomName;
        this.itemCount = itemCount;
        this.warningCount = warningCount;
        this.criticalCount = criticalCount;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getItemID() {return itemID;}
    public String getRoomID() {return roomID;}
    public String getRoomName() {return roomName;}

    public int getItemCount()
    {
        return itemCount;
    }

    public int getWarningCount()
    {
        return warningCount;
    }

    public int getCriticalCount()
    {
        return criticalCount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public void setCriticalCount(int criticalCount) {
        this.criticalCount = criticalCount;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

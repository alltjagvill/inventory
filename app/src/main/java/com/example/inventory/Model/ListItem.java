package com.example.inventory.Model;

import java.io.Serializable;

public class ListItem implements Serializable {

    private String itemName;
    private String itemID;
    private  String roomID;
    private String roomName;
    private int itemCount;
    private int warningCount;
    private int itemCritical;
    private boolean toBuy;

    public ListItem()
    {}

    public ListItem(String itemName, String itemID, String roomID, String roomName, int itemCount, int warningCount, int itemCritical, boolean toBuy)
    {
        this.itemName = itemName;
        this.itemID = itemID;
        this.roomID = roomID;
        this.roomName = roomName;
        this.itemCount = itemCount;
        this.warningCount = warningCount;
        this.itemCritical = itemCritical;
        this.toBuy = toBuy;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getItemID() {return itemID;}
    public String getRoomID() {return roomID;}
    public String getRoomName() {return roomName;}
    public boolean getTobuy() {return toBuy;}

    public int getItemCount()
    {
        return itemCount;
    }

    public int getWarningCount()
    {
        return warningCount;
    }

    public int getItemCritical()
    {
        return itemCritical;
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

    public void setItemCritical(int itemCritical) {
        this.itemCritical = itemCritical;
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

    public void setTobuy(boolean toBuy) { this.toBuy = toBuy;}
}

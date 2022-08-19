package com.example.lentylists;

public class Category {

    private final String TAG = getClass().getSimpleName();

    private int id;
    private String name;
    private int itemCount;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.itemCount = itemCount;
    }


    public void setName(String name) {
        name = name.trim();
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    //TODO make this setter set the amount of inventoryItems in category
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //TODO make this getter return the amount of inventoryItems in category
    public int getItemCount() {
        return itemCount;
    }
}

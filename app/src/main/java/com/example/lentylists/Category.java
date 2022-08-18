package com.example.lentylists;

import android.util.Log;

public class Category {

    private final String TAG = getClass().getSimpleName();

    private int id;
    private String name;
    private int itemCount;


    public Category(int id, String name, int itemCount) {
        this.id = id;
        this.name = name;
        this.itemCount = itemCount;
    }

    //TODO make this setter trim and capitalize name
    public void setName(String name) {
        Log.d(TAG, "setName was called");
        this.name = name;
    }

    //TODO make this setter set the amount of inventoryItems in category
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getId() {
        Log.d(TAG, "getId was called");
        return id;
    }

    public String getName() {
        Log.d(TAG, "getName was called");
        return name;
    }

    //TODO make this getter return the amount of inventoryItems in category
    public int getItemCount() {
        return itemCount;
    }
}

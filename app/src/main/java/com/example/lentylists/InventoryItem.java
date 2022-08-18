package com.example.lentylists;

import android.util.Log;

public class InventoryItem {

    private final String TAG = getClass().getSimpleName();

    private int id;
    private String name = "";
    private int inStockCount;
    private int inUseCount;
    private int categoryId;

    public InventoryItem() {
    }

    public InventoryItem(int id, String name, int inStockCount, int inUseCount, int categoryId) {
        this.id = id;
        this.name = name;
        this.inStockCount = inStockCount;
        this.inUseCount = inUseCount;
        this.categoryId = categoryId;
    }


    public void setName(String name) {
        Log.d(TAG, "setName was called");
        name = name.trim();
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void setInStockCount(int inStockCount) {
        Log.d(TAG, "setInStockCount was called");
        if (inStockCount < 0) {
            this.inStockCount = 0;
            return;
        }
        this.inStockCount = inStockCount;
    }

    public void setInUseCount(int inUseCount) {
        Log.d(TAG, "setInUseCount was called");
        if (inUseCount < 0) {
            this.inUseCount = 0;
            return;
        }
        this.inUseCount = inUseCount;
    }

    public void setCategoryId(int categoryId) {
        Log.d(TAG, "setCategoryId was called");
        this.categoryId = categoryId;
    }

    public int getId() {
        Log.d(TAG, "getId was called");
        return id;
    }

    public String getName() {
        Log.d(TAG, "getName was called");
        return name;
    }

    public int getInStockCount() {
        Log.d(TAG, "getInStockCount was called");
        return inStockCount;
    }

    public int getInUseCount() {
        Log.d(TAG, "getInUseCount was called");
        return inUseCount;
    }

    public int getCategoryId() {
        Log.d(TAG, "getCategoryId was called");
        return categoryId;
    }
}

package com.example.lentylists;

import android.util.Log;

public class Item {

    private final String TAG = getClass().getSimpleName();

    private String name;
    private int inStockCount;
    private int inUseCount;
    private int categoryId;


    public Item(String name, int inStockCount, int inUseCount, int categoryId) {
        this.name = name;
        this.inStockCount = inStockCount;
        this.inUseCount = inUseCount;
        this.categoryId = categoryId;


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

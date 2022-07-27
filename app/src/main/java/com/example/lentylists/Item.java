package com.example.lentylists;

public class Item {

    private final String TAG = getClass().getSimpleName();

    private String name;
    private int inStockCount;
    private int inUseCount;


    public Item(String name, int inStockCount,int inUseCount) {
        this.name = name;
        this.inStockCount = inStockCount;
        this.inUseCount = inUseCount;

    }

    public String getName() {
        return name;
    }

    public int getInStockCount() {
        return inStockCount;
    }

    public int getInUseCount() {
        return inUseCount;
    }
}

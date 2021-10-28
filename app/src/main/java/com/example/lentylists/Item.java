package com.example.lentylists;

public class Item {

    private final String TAG = getClass().getSimpleName();

    private String name;
    private int inUseCount;
    private int inStockCount;

    public Item(String name, int inUseCount, int inStockCount) {
        this.name = name;
        this.inUseCount = inUseCount;
        this.inStockCount = inStockCount;
    }

}

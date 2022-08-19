package com.example.lentylists;

public class InventoryItem {

    private final String TAG = getClass().getSimpleName();

    private int id;
    private String name = "";
    private int inStockCount;
    private int inUseCount;

    public InventoryItem() {
    }

    public InventoryItem(int id, String name, int inStockCount, int inUseCount) {
        this.id = id;
        this.name = name;
        this.inStockCount = inStockCount;
        this.inUseCount = inUseCount;

    }


    public void setName(String name) {
        name = name.trim();
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void setInStockCount(int inStockCount) {
        if (inStockCount < 0) {
            this.inStockCount = 0;
            return;
        }
        this.inStockCount = inStockCount;
    }

    public void setInUseCount(int inUseCount) {
        if (inUseCount < 0) {
            this.inUseCount = 0;
            return;
        }
        this.inUseCount = inUseCount;
    }


    public int getId() {
        return id;
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

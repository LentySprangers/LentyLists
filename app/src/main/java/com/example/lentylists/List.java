package com.example.lentylists;

import android.util.Log;

public class List {

    private final String TAG = getClass().getSimpleName();

    private int id;
    private String name;


    public List(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        Log.d(TAG, "setName was called");
        this.name = name;
    }

    public int getId() {
        Log.d(TAG, "getId was called");
        return id;
    }

    public String getName() {
        Log.d(TAG, "getName was called");
        return name;
    }


}

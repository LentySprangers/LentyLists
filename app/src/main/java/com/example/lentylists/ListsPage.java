package com.example.lentylists;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListsPage extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    Toolbar toolbar;

    DatabaseHelper databaseHelper;
    ArrayList<String> lists = new ArrayList<String>();
    InventoryListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists_page);

        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.lists);

        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(ListsPage.this);
        mFab = findViewById(R.id.fab_lists);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                openAddListPage();
            }
        });


//        adapter = new InventoryListAdapter(this, lists);
//        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        storeDataInArrays();
    }

    private void storeDataInArrays() {
        Log.d(TAG, "storeDataInArrays was called");
    }

    private void openAddListPage() {
        Log.d(TAG, "openAddListPage was called");
    }
}

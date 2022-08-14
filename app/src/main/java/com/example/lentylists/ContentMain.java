package com.example.lentylists;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContentMain extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    Toolbar toolbar;

    DatabaseHelper databaseHelper;
    ArrayList<String> listItems = new ArrayList<String>();
    InventoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_items);

        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(ContentMain.this);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItemPage();
            }
        });


        adapter = new InventoryListAdapter(this, listItems);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storeDataInArrays();
    }

    public void storeDataInArrays() {
        Cursor cursor = databaseHelper.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No inventory items yet", Toast.LENGTH_SHORT).show();
        }
        while (cursor.moveToNext()) {
            listItems.add(cursor.getString(1));
        }
    }


    public void openAddItemPage() {
        Log.d(TAG, "openAddItemPage was called");

        Intent intent = new Intent(this, AddItemPage.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "OnCreateOptionsMenu was called");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "OnOptionsItemSelected was called");


        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




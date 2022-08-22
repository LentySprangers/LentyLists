package com.example.lentylists;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InventoryItemListActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFabAddInventoryItem;
    Toolbar toolbar;

    DatabaseHelper databaseHelper;
    InventoryListAdapter adapter;
    private int categoryId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_item_list_activity);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.inventory_item_recyclerView);

        databaseHelper = new DatabaseHelper(InventoryItemListActivity.this);

        mFabAddInventoryItem = findViewById(R.id.fab_add_inventory_item);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId", 0);
        String categoryName = databaseHelper.readCategoryNameById(categoryId);
        getSupportActionBar().setTitle(categoryName);
        showInventoryItemsInRecyclerView(categoryId);

        mFabAddInventoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                openAddInventoryItemPage(categoryId);
            }
        });
    }

    public void showInventoryItemsInRecyclerView(int categoryId) {
        Log.d(TAG, "showInventoryItemsInRecyclerView was called");
        adapter = new InventoryListAdapter(this, databaseHelper.readInventoryItemByCategoryID(categoryId));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void openAddInventoryItemPage(int categoryId) {
        Log.d(TAG, "openAddItemPage was called");

        Intent intent = new Intent(this, AddInventoryItemPage.class);
        intent.putExtra("CategoryId", categoryId);
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




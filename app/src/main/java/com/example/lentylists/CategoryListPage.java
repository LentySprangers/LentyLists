package com.example.lentylists;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryListPage extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    Toolbar toolbar;

    DatabaseHelper databaseHelper;
    CategoryListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_page);

        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.category_RecyclerView);

        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(CategoryListPage.this);


        FloatingActionButton mFabAddCategory = findViewById(R.id.fab_add_category);
        mFabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                openAddCategoryPage();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        showCategoriesInRecyclerView();
    }

    public void showCategoriesInRecyclerView() {
        Log.d(TAG, "showCategoriesInRecyclerView was called");
        adapter = new CategoryListAdapter(this, databaseHelper.readCategory());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void openAddCategoryPage() {
        Log.d(TAG, "openAddCategoryPage was called");

        Intent intent = new Intent(this, AddCategoryPage.class);
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

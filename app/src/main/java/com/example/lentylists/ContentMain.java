package com.example.lentylists;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContentMain extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();


    ArrayList<String> listItems = new ArrayList<String>();
    private ListView mListView;
    ItemTable itemTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        itemTable = new ItemTable(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItemPage();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "PopulateListView: Displaying data in ListView");

        Cursor data = itemTable.getData();
        listItems.clear();
        while (data.moveToNext()) {
            listItems.add(data.getString(1));


        }

        ListAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

    }

    public void openAddItemPage() {
        Log.d(TAG, "openAddItemPage was called");

        Intent intent = new Intent(this, AddItemPage.class);
        startActivity(intent);
    }

    private void setListAdapter(ListAdapter adapter) {
        Log.d(TAG, "SetListAdapter was called");

        getListView().setAdapter(adapter);
    }

    protected ListView getListView() {
        Log.d(TAG, "getListView was called");

        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.list_item);
        }
        return mListView;
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

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




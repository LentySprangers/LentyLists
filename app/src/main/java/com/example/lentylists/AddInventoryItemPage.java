package com.example.lentylists;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class AddInventoryItemPage extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private TextView inStockCountTextView;
    private TextView inUseCountTextView;
    private Button decrementInStockButton;
    private Button incrementInStockButton;
    private Button decrementInUseButton;
    private Button incrementInUseButton;
    private Button addInventoryItemButton;
    private DatabaseHelper databaseHelper;
    private InventoryItem inventoryItem = new InventoryItem();
    int categoryId;


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        @SuppressLint("NonConstantResourceId")
        public void onClick(View view) {
            Log.d(TAG, "OnClick was called");
            switch (view.getId()) {
                case R.id.increment_in_use:
                    changeInUse(1);
                    break;
                case R.id.decrement_in_use:
                    changeInUse(-1);
                    break;
                case R.id.increment_in_stock:
                    changeInStock(1);
                    break;
                case R.id.decrement_in_stock:
                    changeInStock(-1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        EditText inventoryItemInputFieldEditText = (EditText) findViewById(R.id.category_input_field);
        inventoryItemInputFieldEditText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inventoryItem.setName(editable.toString());
            }
        });


        decrementInStockButton = (Button) findViewById(R.id.decrement_in_stock);
        incrementInStockButton = (Button) findViewById(R.id.increment_in_stock);
        decrementInUseButton = (Button) findViewById(R.id.decrement_in_use);
        incrementInUseButton = (Button) findViewById(R.id.increment_in_use);

        inStockCountTextView = (TextView) findViewById(R.id.in_stock_count);
        inUseCountTextView = (TextView) findViewById(R.id.in_use_count);

        decrementInStockButton.setOnClickListener(clickListener);
        incrementInStockButton.setOnClickListener(clickListener);
        decrementInUseButton.setOnClickListener(clickListener);
        incrementInUseButton.setOnClickListener(clickListener);

        initCounters();

        addInventoryItemButton = (Button) findViewById(R.id.add_category_button);

        // Add inventoryItem to category
        addInventoryItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                if (inventoryItem.getName().equals("")) {
                    Snackbar.make(view, "Please enter an item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    return;
                }


                addInventoryItem(inventoryItem, categoryId, view);
                finish();

            }
        });

        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("CategoryId", 0);
    }

    private void initCounters() {
        Log.d(TAG, "initCounters was called");

        inStockCountTextView.setText("" + inventoryItem.getInStockCount());

        inUseCountTextView.setText("" + inventoryItem.getInUseCount());
    }

    private void changeInStock(int change) {
        Log.d(TAG, "changeInStock was called");

        inventoryItem.setInStockCount(inventoryItem.getInStockCount() + change);
        inStockCountTextView.setText("" + inventoryItem.getInStockCount());

    }


    private void changeInUse(int change) {
        Log.d(TAG, "changeInUse was called");

        inventoryItem.setInUseCount(inventoryItem.getInUseCount() + change);
        inUseCountTextView.setText("" + inventoryItem.getInUseCount());
    }

    private void addInventoryItem(InventoryItem inventoryItem, int categoryId, View view) {
        Log.d(TAG, "addInventoryItem was called");


        boolean insertData = databaseHelper.createInventoryItem(inventoryItem, categoryId);

        if (insertData) {
            Snackbar.make(view, inventoryItem.getName() + " was added to list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else {
            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
package com.example.lentylists;

import android.annotation.SuppressLint;
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

public class AddItemPage extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private EditText editText;
    private Button decrementInStock;
    private TextView inStockCounter;
    private int stockCounter;
    private Button incrementInStock;
    private Button decrementInUse;
    private TextView inUseCounter;
    private int useCounter;
    private Button incrementInUse;
    private Button addItemButton;
    private String itemName = "";
    private DatabaseHelper databaseHelper;


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        @SuppressLint("NonConstantResourceId")
        public void onClick(View view) {
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

        editText = (EditText) findViewById(R.id.input_field);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                itemName = editable.toString();
            }
        });


        decrementInStock = (Button) findViewById(R.id.decrement_in_stock);
        decrementInStock.setOnClickListener(clickListener);
        inStockCounter = (TextView) findViewById(R.id.in_stock_count);
        incrementInStock = (Button) findViewById(R.id.increment_in_stock);
        incrementInStock.setOnClickListener(clickListener);

        decrementInUse = (Button) findViewById(R.id.decrement_in_use);
        decrementInUse.setOnClickListener(clickListener);
        inUseCounter = (TextView) findViewById(R.id.in_use_count);
        incrementInUse = (Button) findViewById(R.id.increment_in_use);
        incrementInUse.setOnClickListener(clickListener);

        initCounters();

        addItemButton = (Button) findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                if (itemName.equals("")) {
                    Snackbar.make(view, "Please enter an item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    itemName = itemName.trim().substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase();
                    addData(itemName, stockCounter, useCounter, view);
                    finish();
                }
            }
        });

        databaseHelper = new DatabaseHelper(this);

    }

    private void initCounters() {
        stockCounter = 0;
        inStockCounter.setText("" + stockCounter);
        useCounter = 0;
        inUseCounter.setText("" + useCounter);
    }

    private void changeInStock(int change) {
        stockCounter += change;
        if (stockCounter < 0) {
            stockCounter = 0;
        }
        inStockCounter.setText("" + stockCounter);

    }


    private void changeInUse(int change) {
        useCounter += change;
        if (useCounter < 0) {
            useCounter = 0;
        }
        inUseCounter.setText("" + useCounter);
    }

    private void addData(String itemName, int stockCounter, int useCounter, View view) {
        Log.d(TAG, "addData was called");

        boolean insertData = databaseHelper.addData(itemName, stockCounter, useCounter);

        if (insertData) {
            Snackbar.make(view, itemName + " was added to list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
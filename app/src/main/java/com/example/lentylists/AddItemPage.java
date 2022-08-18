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
    private TextView inStockCounter;
    private TextView inUseCounter;
    private Button decrementInStock;
    private Button incrementInStock;
    private Button decrementInUse;
    private Button incrementInUse;
    private Button addItemButton;
    private DatabaseHelper databaseHelper;
    private Item item = new Item();


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

        editText = (EditText) findViewById(R.id.input_field);
        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                item.setName(editable.toString());
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

        // Add inventoryItem to category
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                if (item.getName().equals("")) {
                    Snackbar.make(view, "Please enter an item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    return;
                }

                addData(item, view);
                finish();

            }
        });

        databaseHelper = new DatabaseHelper(this);

    }

    private void initCounters() {
        Log.d(TAG, "initCounters was called");

        inStockCounter.setText("" + item.getInStockCount());

        inUseCounter.setText("" + item.getInUseCount());
    }

    private void changeInStock(int change) {
        Log.d(TAG, "changeInStock was called");

        item.setInStockCount(item.getInStockCount() + change);
        inStockCounter.setText("" + item.getInStockCount());

    }


    private void changeInUse(int change) {
        Log.d(TAG, "changeInUse was called");

        item.setInUseCount(item.getInUseCount() + change);
        inUseCounter.setText("" + item.getInUseCount());
    }

    private void addData(Item item, View view) {
        Log.d(TAG, "addData was called");

        boolean insertData = databaseHelper.createItem(item);

        if (insertData) {
            Snackbar.make(view, item.getName() + " was added to list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else {
            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
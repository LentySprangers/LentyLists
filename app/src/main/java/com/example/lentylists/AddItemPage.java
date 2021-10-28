package com.example.lentylists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class AddItemPage extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    Button button;
    EditText editText;
    String itemName = "";
    DatabaseHelper databaseHelper;

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

        button = (Button) findViewById(R.id.add_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                if (itemName.equals("")) {
                    Snackbar.make(view, "Please enter a item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    itemName = itemName.trim().substring(0, 1).toUpperCase() + itemName.substring(1).toLowerCase();
                    addData(itemName, view);
                    finish();
                }
            }
        });

        databaseHelper = new DatabaseHelper(this);

    }

    public void addData(String itemName, View view) {
        Log.d(TAG, "addData was called");

        boolean insertData = databaseHelper.addData(itemName);

        if (insertData) {
            Snackbar.make(view, itemName + " was added to list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
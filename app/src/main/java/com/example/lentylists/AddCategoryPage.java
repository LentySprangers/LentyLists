package com.example.lentylists;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class AddCategoryPage extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private DatabaseHelper databaseHelper;
    private Category category = new Category();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreate was called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText categoryInputFieldEditText = (EditText) findViewById(R.id.category_input_field);
        categoryInputFieldEditText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                category.setName(editable.toString());
            }
        });


        Button addCategoryButton = (Button) findViewById(R.id.add_category_button);

        // Add category
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick was called");

                if (category.getName().equals("")) {
                    Snackbar.make(view, "Please enter a category", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    return;
                }

                addCategory(category, view);
                finish();

            }
        });

        databaseHelper = new DatabaseHelper(this);

    }


    private void addCategory(Category category, View view) {
        Log.d(TAG, "addCategory was called");

        boolean insertData = databaseHelper.createCategory(category);

        if (insertData) {
            Snackbar.make(view, category.getName() + " was added to list", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else {
            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}

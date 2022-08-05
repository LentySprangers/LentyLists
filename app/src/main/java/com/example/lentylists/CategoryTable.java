package com.example.lentylists;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CategoryTable extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private static final String TABLE_NAME = "Category";
    private static final String COL1 = "ID";


    public CategoryTable(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "OnCreate was called");

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "OnUpgrade was called");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}

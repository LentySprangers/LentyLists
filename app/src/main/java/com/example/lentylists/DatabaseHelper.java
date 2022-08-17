package com.example.lentylists;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private static final String TABLE_NAME = "Item";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "inStock";
    private static final String COL4 = "inUse";
    private static final String COL5 = "categoryId";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "OnCreate was called");

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT NOT NULL, " + COL3 + " INTEGER, " + COL4 + " INTEGER, " + COL5 + " INTEGER);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "OnUpgrade was called");

        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD " + COL5);
        onCreate(db);
    }

    public boolean addData(String item, int inStock, int inUse, int categoryId) {
        Log.d(TAG, "addData was called");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, inStock);
        contentValues.put(COL4, inUse);
        contentValues.put(COL5, categoryId);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        return result != -1;
    }

    public Cursor getData() {
        Log.d(TAG, "getData was called");

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

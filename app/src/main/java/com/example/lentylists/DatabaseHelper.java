package com.example.lentylists;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private static final String INVENTORY_ITEM_TABLE = "InventoryItem";
    private static final String COL_INVENTORY_ITEM_ID = "ID";
    private static final String COL_INVENTORY_ITEM_NAME = "name";
    private static final String COL_INVENTORY_ITEM_IN_STOCK = "inStock";
    private static final String COL_INVENTORY_ITEM_IN_USE = "inUse";
    private static final String COL_INVENTORY_ITEM_CATEGORY_ID = "categoryId";


    public DatabaseHelper(Context context) {

        super(context, "LentyListsDatabase.db", null, 1);
    }

    @Override
    // this is called the first time a database is accessed. This method creates a new database
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "OnCreate was called");

        String createTable = "CREATE TABLE " + INVENTORY_ITEM_TABLE + " (" + COL_INVENTORY_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_INVENTORY_ITEM_NAME + " TEXT NOT NULL, " + COL_INVENTORY_ITEM_IN_STOCK + " INTEGER, " + COL_INVENTORY_ITEM_IN_USE + " INTEGER, " + COL_INVENTORY_ITEM_CATEGORY_ID + " INTEGER)";

        db.execSQL(createTable);
    }

    @Override
    // this is called if the database version number changes. it prevents previous users apps from breaking when you change the database design.
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "OnUpgrade was called");

        db.execSQL("ALTER TABLE " + INVENTORY_ITEM_TABLE + " ADD " + COL_INVENTORY_ITEM_CATEGORY_ID);
        onCreate(db);
    }


    public boolean createInventoryItem(InventoryItem inventoryItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_INVENTORY_ITEM_NAME, inventoryItem.getName());
        contentValues.put(COL_INVENTORY_ITEM_IN_STOCK, inventoryItem.getInStockCount());
        contentValues.put(COL_INVENTORY_ITEM_IN_USE, inventoryItem.getInUseCount());
        contentValues.put(COL_INVENTORY_ITEM_CATEGORY_ID, inventoryItem.getCategoryId());

        long result = db.insert(INVENTORY_ITEM_TABLE, null, contentValues);
        // if data is inserted incorrectly it will return -1
        return result != -1;
    }

    public ArrayList<InventoryItem> ReadInventoryItem() {
        Log.d(TAG, "readInventoryItem was called");

        ArrayList<InventoryItem> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + INVENTORY_ITEM_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();


        if (db == null) {
            Log.e(TAG, "Couldn't instantiate database instance to retrieve inventoryItemList");
            return returnList;
        }

        Cursor cursor = db.rawQuery(query, null);

        if (!cursor.moveToFirst()) {
            Log.d(TAG, "There were no inventoryItems set in db");
            return returnList;
        }

        do {
            int inventoryItemId = cursor.getInt(0);
            String inventoryItemName = cursor.getString(1);
            int inStockCount = cursor.getInt(2);
            int inUseCount = cursor.getInt(3);
            int categoryId = cursor.getInt(4);

            InventoryItem newInventoryItem = new InventoryItem(inventoryItemId, inventoryItemName, inStockCount, inUseCount, categoryId);
            returnList.add(newInventoryItem);


        } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return returnList;

    }

    // TODO read category from db
    public ArrayList<Category> readCategory() {
        Log.d(TAG, "readCategory was called");

        ArrayList<Category> returnList = new ArrayList<>();
        return returnList;
    }
}

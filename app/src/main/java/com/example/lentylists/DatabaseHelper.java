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

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LentyListsDatabase.db";

    // table names
    private static final String INVENTORY_ITEM_TABLE = "InventoryItem";
    private static final String CATEGORY_TABLE = "Category";
    private static final String LINK_INVENTORY_ITEM_CATEGORY = "Link_InventoryItem_Category";

    // InventoryItem column names
    private static final String COL_INVENTORY_ITEM_ID = "InventoryItemID";
    private static final String COL_INVENTORY_ITEM_NAME = "name";
    private static final String COL_INVENTORY_ITEM_IN_STOCK = "inStock";
    private static final String COL_INVENTORY_ITEM_IN_USE = "inUse";

    // Category column names
    private static final String COL_CATEGORY_ID = "CategoryID";
    private static final String COL_CATEGORY_NAME = "name";

    // Link_InventoryItem_Category column names
    private static final String COL_LINK_INVENTORY_ITEM_CATEGORY_ID = "id";
    private static final String COL_LINK_CATEGORY_ID = "CategoryID";
    private static final String COL_LINK_INVENTORY_ITEM_ID = "InventoryItemID";

    // CREATE TABLE queries
    private static final String CREATE_TABLE_INVENTORY_ITEM = "CREATE TABLE "
            + INVENTORY_ITEM_TABLE + " ("
            + COL_INVENTORY_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_INVENTORY_ITEM_NAME + " TEXT NOT NULL, "
            + COL_INVENTORY_ITEM_IN_STOCK + " INTEGER, "
            + COL_INVENTORY_ITEM_IN_USE + " INTEGER)";


    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + CATEGORY_TABLE + " ("
            + COL_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_CATEGORY_NAME + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_LINK_INVENTORY_ITEM_CATEGORY = "CREATE TABLE "
            + LINK_INVENTORY_ITEM_CATEGORY + " ("
            + COL_LINK_INVENTORY_ITEM_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_LINK_CATEGORY_ID + " INTEGER REFERENCES " + CATEGORY_TABLE + "(" + COL_CATEGORY_ID + "), "
            + COL_LINK_INVENTORY_ITEM_ID + " INTEGER REFERENCES " + INVENTORY_ITEM_TABLE + "(" + COL_INVENTORY_ITEM_ID + "))";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    // this is called the first time a database is accessed. This method creates a new database
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "OnCreate was called");

        // creating required tables
        db.execSQL(CREATE_TABLE_INVENTORY_ITEM);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_LINK_INVENTORY_ITEM_CATEGORY);

    }

    @Override
    // this is called if the database version number changes. it prevents previous users apps from breaking when you change the database design.
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "OnUpgrade was called");

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + INVENTORY_ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LINK_INVENTORY_ITEM_CATEGORY);

        // and create new tables
        onCreate(db);

    }


    public boolean createInventoryItem(InventoryItem inventoryItem, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_INVENTORY_ITEM_NAME, inventoryItem.getName());
        contentValues.put(COL_INVENTORY_ITEM_IN_STOCK, inventoryItem.getInStockCount());
        contentValues.put(COL_INVENTORY_ITEM_IN_USE, inventoryItem.getInUseCount());


        int inventoryItemId = (int) db.insert(INVENTORY_ITEM_TABLE, null, contentValues);
        if (inventoryItemId == -1) {
            Log.e(TAG, "InventoryItem could not be inserted in the database");
            return false;
        }

        ContentValues cv = new ContentValues();
        cv.put(COL_LINK_INVENTORY_ITEM_ID, inventoryItemId);
        cv.put(COL_LINK_CATEGORY_ID, categoryId);


        long result = db.insert(LINK_INVENTORY_ITEM_CATEGORY, null, cv);
        db.close();
        return result != -1;

    }


    public ArrayList<InventoryItem> readInventoryItemByCategoryID(int categoryId) {

        ArrayList<InventoryItem> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + INVENTORY_ITEM_TABLE + " AS I INNER JOIN " + LINK_INVENTORY_ITEM_CATEGORY + " AS LINK ON I."
                + COL_INVENTORY_ITEM_ID + " = LINK." + COL_INVENTORY_ITEM_ID + " INNER JOIN "
                + LINK_INVENTORY_ITEM_CATEGORY + " AS LINK2 ON I." + COL_INVENTORY_ITEM_ID + " = LINK2."
                + COL_INVENTORY_ITEM_ID + " WHERE LINK." + COL_LINK_CATEGORY_ID + " = " + categoryId;


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


            InventoryItem newInventoryItem = new InventoryItem(inventoryItemId, inventoryItemName, inStockCount, inUseCount);
            returnList.add(newInventoryItem);


        } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return returnList;
    }

    public ArrayList<InventoryItem> readInventoryItem() {
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


            InventoryItem newInventoryItem = new InventoryItem(inventoryItemId, inventoryItemName, inStockCount, inUseCount);
            returnList.add(newInventoryItem);


        } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return returnList;

    }

    public boolean createCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CATEGORY_NAME, category.getName());


        long result = db.insert(CATEGORY_TABLE, null, contentValues);
        // if data is inserted incorrectly it will return -1
        return result != -1;
    }


    public ArrayList<Category> readCategory() {
        Log.d(TAG, "readCategory was called");

        ArrayList<Category> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + CATEGORY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();


        if (db == null) {
            Log.e(TAG, "Couldn't instantiate database instance to retrieve CategoryList");
            return returnList;
        }

        Cursor cursor = db.rawQuery(query, null);

        if (!cursor.moveToFirst()) {
            Log.d(TAG, "There were no categories set in db");
            return returnList;
        }

        do {
            int categoryId = cursor.getInt(0);
            String categoryName = cursor.getString(1);


            Category newCategory = new Category(categoryId, categoryName);
            returnList.add(newCategory);


        } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return returnList;
    }
}

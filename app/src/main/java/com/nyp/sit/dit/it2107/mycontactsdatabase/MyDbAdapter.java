package com.nyp.sit.dit.it2107.mycontactsdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbAdapter {
    private static final String DATABASE_NAME = "test.db";
    private static final String DATABASE_TABLE = "myTestDb";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase _db;
    private final Context context;

    public static final String KEY_ID = "_id";
    public static final int COLUMN_KEY_ID = 0;
    public static final String ENTRY_NAME = "entry_name";
    public static final int COLUMN_NAME_ID = 1;
    public static final String ENTRY_TEL = "entry_telephone";
    public static final int COLUMN_TEL_ID = 2;

    protected static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " " + "(" + KEY_ID + " integer primary key autoincrement, " + ENTRY_NAME + " Text, "
            + ENTRY_TEL + " text not null);";

    private String MYDBADAPTER_LOG_CAT = "MY_LOG";

    private MyDBOpenHelper dbHelper;

    public MyDbAdapter(Context _context) {
        this.context = _context;
        dbHelper = new MyDBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);


        //step 16 - create MyDBOpenHelper object


    }

    public void close() {

        _db.close();
        Log.w(MYDBADAPTER_LOG_CAT, "DB closed");
        //step 17 - close db

    }

    public void open() throws SQLiteException {

        try {
            _db = dbHelper.getWritableDatabase();
            Log.w(MYDBADAPTER_LOG_CAT, "DB closed");
        } catch (SQLiteException e) {

            _db = dbHelper.getWritableDatabase();
            Log.w(MYDBADAPTER_LOG_CAT, "DB opened as readable database");

        }
        //step 18 - open db


    }

    public long insertEntry(String entryName, String entryTel) {
        ContentValues newEntryValues = new ContentValues();

        newEntryValues.put(ENTRY_NAME, entryName);
        newEntryValues.put(ENTRY_TEL, entryTel);

        Log.w(MYDBADAPTER_LOG_CAT, "Inserted EntryName = " + entryName + "EntryTel = " +
                entryTel + "into table" + DATABASE_TABLE);

        return _db.insert(DATABASE_TABLE, null, newEntryValues);
        //step 19 - insert record into table

    }

    public boolean removeEntry(long _rowIndex) {

        if (_db.delete(DATABASE_TABLE, KEY_ID + "=" + _rowIndex, null) <= 0) {
            Log.w(MYDBADAPTER_LOG_CAT, "Removing entry where id = " + _rowIndex + "Failed");
            return false;
        }
        Log.w(MYDBADAPTER_LOG_CAT, "Removing entry where id = " + _rowIndex + "Success");
        //step 20 - remove record from table


        return true;

    }

    public boolean updateEntry(long _rowIndex, String entryName, String entryTel) {


        return false;
    }

    public Cursor retrieveAllEntriesCursor() {

        Cursor c = null;

        try {
            c = _db.query(DATABASE_TABLE, new String[]{KEY_ID, ENTRY_NAME, ENTRY_TEL},
                    null, null, null, null, null);
        } catch (SQLiteException e) {
            Log.w(MYDBADAPTER_LOG_CAT, "Retrieve fail!");

        }

        return c;
        //step 21 - retrieve all records from table
    }

    public class MyDBOpenHelper extends SQLiteOpenHelper {
        public MyDBOpenHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL(DATABASE_CREATE);
            Log.w(MYDBADAPTER_LOG_CAT, "Helper : DB " + DATABASE_TABLE + "Created!!");
            //step 14 - execute create sql statement


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }

    } // End of myDBOpenHelper

}// End of myDBAdapter


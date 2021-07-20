package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Transaction_helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaction.db";
    private static final int DATABASE_VERSION = 1;



    public DB_Transaction_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TRANSACTION_TABLE = "CREATE TABLE " + DB_Transaction_Contract.Transaction_Entry.TABLE_NAME +
                " (" + DB_Transaction_Contract.Transaction_Entry.COLUMN_FROM_NAME +" TEXT, " +
                   DB_Transaction_Contract.Transaction_Entry.COLUMN_TO_NAME + " TEXT, " +
                  DB_Transaction_Contract.Transaction_Entry.COLUMN_AMOUNT + " INTEGER, " +
                DB_Transaction_Contract.Transaction_Entry.COLUMN_STATUS + " INTEGER )" ;

        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       if(oldVersion != newVersion)
       {
          db.execSQL("DROP TABLE IF EXISTS " + DB_Transaction_Contract.Transaction_Entry.TABLE_NAME);
          onCreate(db);
       }

    }

    public boolean insert_TrannferData(String fromName, String toName, String amount, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DB_Transaction_Contract.Transaction_Entry.COLUMN_FROM_NAME, fromName);
        values.put(DB_Transaction_Contract.Transaction_Entry.COLUMN_TO_NAME, toName);
        values.put(DB_Transaction_Contract.Transaction_Entry.COLUMN_AMOUNT, amount);
        values.put(DB_Transaction_Contract.Transaction_Entry.COLUMN_STATUS, status);

        Long result = db.insert(DB_Transaction_Contract.Transaction_Entry.TABLE_NAME, null, values);

        if (result == -1) {
            return false;
        }
        else
        {
            return true;
        }
    }


}

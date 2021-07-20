package com.example.basicbankingapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class user_helper extends SQLiteOpenHelper {

    String TABLE_NAME = user_Contract.user_Entry.TABLE_NAME;

    public static final String DATABASE_NAME = "user.db";

    public static final int DATABASE_VERSION = 1;

    public user_helper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

     String SQL_CREATE_USER_TABLE = "CREATE TABLE " +user_Contract.user_Entry.TABLE_NAME + " ("
             + user_Contract.user_Entry.COLUMN_USER_NAME +" TEXT," + user_Contract.user_Entry.COLUMN_USER_ACCOUNT_NO +
             " INTEGER, " + user_Contract.user_Entry.COLUMN_USER_EMAIL + " TEXT, " + user_Contract.user_Entry.COLUMN_PHONE_NO +
             " TEXT," + user_Contract.user_Entry.COLUMN_BALANCE + " INTEGER, " +  user_Contract.user_Entry.COLUMN_USER_IFSC_CODE + " TEXT);";

     db.execSQL(SQL_CREATE_USER_TABLE);

     db.execSQL("insert into "+TABLE_NAME+" values('Tarun Gupta',134502343,'tarunguptaraja@gmail.com','9876543210',1589788,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Gambhir Gupta',134507676,'gambhirgupta@gmail.com','8978675602',167878,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Rohit Sharma',134508798,'rohitsharma@gmail.com','6398786756',209080,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Remo Desuja',134505645,'desujaramo@gmail.com','9824569878',145640,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Faisal Khan',134507867,'khanfaisal523@gmail.com','8978679808',2567,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Saif Ali Khan',134506987,'alisaifkhan@gmail.com','9823435467',60900,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Shanaya Sidiqqi',134506767,'sidiqqisanaya@gmail.com','8721789867',59007,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Shirley Setia',134508282,'teamshirley@gmail.com','7855659089',143786,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Gauri Khan',134504876,'khangauri123@gmail.com','6921215678',56787,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Anna George',134508799,'georgeanna@gmail.com','7891296756',45780,'PBKS0001345')");
     db.execSQL("insert into "+TABLE_NAME+" values('Fatima Mishra',134507695,'mishrafatima@gmail.com','8228978777',309090,'PBKS0001345')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion != newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+ user_Contract.user_Entry.TABLE_NAME);
            onCreate(db);
        }

    }

    public Cursor readData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + user_Contract.user_Entry.TABLE_NAME,null);
        return cursor;
    }

    public Cursor readPartiCularData(int accountNo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ user_Contract.user_Entry.TABLE_NAME+" where "+
                user_Contract.user_Entry.COLUMN_USER_ACCOUNT_NO+" = "+accountNo,null);

        return cursor;
    }

    public void amountUpdate(int accountNo, int amount)
    {
        Log.d("tag","update amount");
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("update "+user_Contract.user_Entry.TABLE_NAME+" set "+user_Contract.user_Entry.COLUMN_BALANCE+
                " = " +amount + " where " +user_Contract.user_Entry.COLUMN_USER_ACCOUNT_NO + " = " + accountNo);
    }
}

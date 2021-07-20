package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Transaction_history extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Transaction> Transaction_ArrayList;

    private DB_Transaction_helper db_transactionHelper;

    TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        emptyList = findViewById(R.id.empty_text);

        // Create transaction history list
        Transaction_ArrayList = new ArrayList<Transaction>();

        //Create database table
        db_transactionHelper = new DB_Transaction_helper(this);

        //Display database information
        DatabaseInfo();

        recyclerView = findViewById(R.id.transactions_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new TransactionHistory_Adapter(this,Transaction_ArrayList);
        recyclerView.setAdapter(myAdapter);
    }

    private void DatabaseInfo()
    {
        Log.d("TAG", "displayDataBaseInfo()");

        // Create and/or open a database to read from it
        SQLiteDatabase db = db_transactionHelper.getReadableDatabase();

        Log.d("TAG", "displayDataBaseInfo()1");

        String[] projection = {
                DB_Transaction_Contract.Transaction_Entry.COLUMN_FROM_NAME,
                DB_Transaction_Contract.Transaction_Entry.COLUMN_TO_NAME,
                DB_Transaction_Contract.Transaction_Entry.COLUMN_AMOUNT,
                DB_Transaction_Contract.Transaction_Entry.COLUMN_STATUS
        };

        Log.d("Tag","displayDataBaseInfo()2");

        Cursor cursor = db.query(DB_Transaction_Contract.Transaction_Entry.TABLE_NAME,projection,
                null,null,null,null,null);

        //Index
        int fromNameColumn_Index = cursor.getColumnIndex(DB_Transaction_Contract.Transaction_Entry.COLUMN_FROM_NAME);
        int ToNameColumn_Index = cursor.getColumnIndex(DB_Transaction_Contract.Transaction_Entry.COLUMN_TO_NAME);
        int amountColumn_Index = cursor.getColumnIndex(DB_Transaction_Contract.Transaction_Entry.COLUMN_AMOUNT);
        int statusColumn_Index = cursor.getColumnIndex(DB_Transaction_Contract.Transaction_Entry.COLUMN_STATUS);

        Log.d("Tag","displayDatabaseInfo");

        while (cursor.moveToNext())
        {
            String fromName = cursor.getString(0);
            String toName = cursor.getString(1);
            int amount = cursor.getInt(amountColumn_Index);
            int status = cursor.getInt(statusColumn_Index);

            Transaction_ArrayList.add(new Transaction(toName,fromName,amount,status));
        }

        cursor.close();

        if(Transaction_ArrayList.isEmpty())
        {
           emptyList.setVisibility(View.VISIBLE);
        }
        else
        {
            emptyList.setVisibility(View.GONE);
        }

    }
}
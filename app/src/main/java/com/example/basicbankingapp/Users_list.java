package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class Users_list extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    ArrayList<User> userArrayList;
    RecyclerView.LayoutManager layoutManager;

    private user_helper db_userHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        //Create users arrayList
        userArrayList = new ArrayList<User>();

        //Create table in the database
        db_userHelper = new user_helper(this);

        displayDatabaseInfo();

        recyclerView = findViewById(R.id.all_users_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new userList_Adapter(this,userArrayList);
        recyclerView.setAdapter(myAdapter);
    }

    private void displayDatabaseInfo()
    {   userArrayList.clear();

        Cursor cursor = new user_helper(this).readData();

        //Index
        int nameColumnIndex = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_NAME);
        int accountNoColumnIndex = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_ACCOUNT_NO);
        int emailColumnIndex = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_EMAIL);
        int phoneColumnIndex = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_PHONE_NO);
        int balanceIndex = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_BALANCE);
        int ifscIndex = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_IFSC_CODE);

        while (cursor.moveToNext())
        {
           String Currentname = cursor.getString(nameColumnIndex);
           int Account = cursor.getInt(accountNoColumnIndex);
           String email = cursor.getString(emailColumnIndex);
           String phone = cursor.getString(phoneColumnIndex);
           int balance = cursor.getInt(balanceIndex);
           String IFSC = cursor.getString(ifscIndex);

            userArrayList.add(new User(Currentname,Account,email,phone,balance,IFSC));
        }



    }
}
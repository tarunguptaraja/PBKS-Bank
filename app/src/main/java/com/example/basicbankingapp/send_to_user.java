package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class send_to_user extends AppCompatActivity implements sendToUser_Adapter.onUserListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<User> userArrayList;

    private user_helper db_helper;

    String date=null, time=null;
    int fromUserAccountNo, toUserAccountNo, toUserAccountBalance;
    String fromUserAccountName, fromUserAccountBalance, transferAmount, toUserAccountName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to_user);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy, hh:mm a");
        String data_and_time = simpleDateFormat.format(calendar.getTime());

        Bundle bundle = getIntent().getExtras();

        if(bundle!= null)
        {
          fromUserAccountNo = bundle.getInt("FROM_USER_ACCOUNT_NO");
          fromUserAccountName = bundle.getString("FROM_USER_ACCOUNT_NAME");
          fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE");
          transferAmount = bundle.getString("TRANSFER_AMOUNT");
        }

        userArrayList = new ArrayList<User>();

        //Create table in the database
        db_helper = new user_helper(this);

        //show view in the list
        recyclerView = findViewById(R.id.all_users_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new sendToUser_Adapter(userArrayList,this);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        displayDatabaseInfo();
    }


    @Override
    public void onUserClick(int position) {
        //Data insertion in the transaction table

      toUserAccountNo = userArrayList.get(position).getAccountNo();
      toUserAccountName = userArrayList.get(position).getName();
      toUserAccountBalance = userArrayList.get(position).getBalance();

      calculateOfAmount();

        new DB_Transaction_helper(this).insert_TrannferData(fromUserAccountName,toUserAccountName,transferAmount,1);
        Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(send_to_user.this,MainActivity.class));
        finish();

    }

    public void calculateOfAmount(){

        int fromUserAcc = Integer.parseInt(fromUserAccountBalance);
        int tranferAmount = Integer.parseInt(transferAmount);
        int remainingAmount = fromUserAcc - tranferAmount;
        int latestAmount = tranferAmount + toUserAccountBalance;

        //amount update in database
        new user_helper(this).amountUpdate(fromUserAccountNo,remainingAmount);
        new user_helper(this).amountUpdate(toUserAccountNo,latestAmount);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void displayDatabaseInfo() {

        SQLiteDatabase db = db_helper.getReadableDatabase();

        String[] projection = { user_Contract.user_Entry.COLUMN_USER_NAME, user_Contract.user_Entry.COLUMN_USER_ACCOUNT_NO,
                      user_Contract.user_Entry.COLUMN_BALANCE,user_Contract.user_Entry.COLUMN_USER_IFSC_CODE,
                 user_Contract.user_Entry.COLUMN_PHONE_NO,user_Contract.user_Entry.COLUMN_USER_EMAIL};

        Cursor cursor = db.query(user_Contract.user_Entry.TABLE_NAME,
                projection,null,null,null,null,null);

        //Index of each column
        int phone_Column = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_PHONE_NO);
        int  balance_Column = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_BALANCE);
        int  email_Column = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_EMAIL);
        int  ifsc_Column = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_IFSC_CODE);
        int accountNo_Column = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_ACCOUNT_NO);
        int userName_Column = cursor.getColumnIndex(user_Contract.user_Entry.COLUMN_USER_NAME);

       while (cursor.moveToNext())
       {
          String CurrentName = cursor.getString(userName_Column);
          String phone = cursor.getString(phone_Column);
          int balance = cursor.getInt(balance_Column);
          String email = cursor.getString(email_Column);
          String ifsc = cursor.getString(ifsc_Column);
          int accountNo = cursor.getInt(accountNo_Column);

         //Display each column of current row
          userArrayList.add(new User(CurrentName,accountNo,email,phone,balance,ifsc));

       }
     // Close the cursor when the reading has been done
     cursor.close();

    }
}
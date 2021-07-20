package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class User_data extends AppCompatActivity {

    TextView name, AccNo, email, phone, ifsc, balance;
    Button btn_TranferMoney;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        name = findViewById(R.id.tv_name);
        AccNo = findViewById(R.id.tv_account);
        email = findViewById(R.id.tv_email);
        phone = findViewById(R.id.tv_mobile);
        ifsc = findViewById(R.id.tv_ifsc);
        balance = findViewById(R.id.tv_balance);

        btn_TranferMoney = findViewById(R.id.btn_transfer_money);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null)
        {
           name.setText(extras.getString("NAME"));
           AccNo.setText(String.valueOf(extras.getInt("ACCOUNT")));
           email.setText(extras.getString("EMAIL"));
           phone.setText(extras.getString("PHONE"));
           ifsc.setText(extras.getString("IFSC"));
           balance.setText(String.valueOf(extras.getInt("BALANCE")));

        }

        btn_TranferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     enterAmount();
            }
        });

    }

    private void enterAmount()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_box,null);
        builder.setTitle("Enter amount").setView(view).setCancelable(false);

        EditText enterAmount = view.findViewById(R.id.enter_money);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                transaction_Cancel();

            }
        });

        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int currentBalance = Integer.parseInt(String.valueOf(balance.getText()));

               if(enterAmount.getText().toString().isEmpty())
               {
                   enterAmount.setError("Please enter some amount");
               } else
                   if(Integer.parseInt(enterAmount.getText().toString()) > currentBalance)
                   {
                       enterAmount.setError("Account doesn't have enough balance");
                   } else
                   {
                       Intent intent = new Intent(User_data.this,send_to_user.class);

                       intent.putExtra("FROM_USER_ACCOUNT_NO",Integer.parseInt(AccNo.getText().toString()));
                       intent.putExtra("FROM_USER_ACCOUNT_NAME",name.getText().toString());
                       intent.putExtra("FROM_USER_ACCOUNT_BALANCE",balance.getText().toString());
                       intent.putExtra("TRANSFER_AMOUNT",enterAmount.getText().toString());

                       startActivity(intent);
                       finish();
                   }
            }
        });

    }

    private void transaction_Cancel()
    {
        AlertDialog.Builder alert_ExitButton = new AlertDialog.Builder(User_data.this);

        alert_ExitButton.setTitle("Do you want to cancel the Transaction?").setCancelable(false);

        alert_ExitButton.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(User_data.this, "Transaction cancelled !", Toast.LENGTH_SHORT).show();
            }
        });

        alert_ExitButton.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

              dialog.dismiss();
              enterAmount();
            }
        });

        AlertDialog alertExit = alert_ExitButton.create();
        alertExit.show();
    }
}
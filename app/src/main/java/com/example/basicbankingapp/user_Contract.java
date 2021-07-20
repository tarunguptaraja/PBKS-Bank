package com.example.basicbankingapp;

import android.provider.BaseColumns;

public class user_Contract {

    private user_Contract()
    {

    }

    public final static class user_Entry implements BaseColumns  {

        public final static String TABLE_NAME = "User_Table";

        public static final String ID = "_ID";
        public static final String COLUMN_USER_NAME = "name";
        public static final String COLUMN_USER_ACCOUNT_NO = "accountNo";
        public static final String COLUMN_USER_IFSC_CODE = "ifscCode";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_PHONE_NO = "phoneNo";
        public static final String COLUMN_BALANCE = "balance";
    }




}

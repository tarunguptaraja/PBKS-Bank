package com.example.basicbankingapp;

import android.provider.BaseColumns;

public class DB_Transaction_Contract {

    private DB_Transaction_Contract() {
    }

    public static final class Transaction_Entry implements BaseColumns {
      //Name of the database table
      public final static String TABLE_NAME = "Transaction_Table";

      public final static String _ID = BaseColumns._ID;
      public final static String COLUMN_FROM_NAME = "from_Name";
      public final static String COLUMN_TO_NAME = "to_Name";
      public final static String COLUMN_AMOUNT = "amount";
      public final static String COLUMN_STATUS = "status";

    }
}

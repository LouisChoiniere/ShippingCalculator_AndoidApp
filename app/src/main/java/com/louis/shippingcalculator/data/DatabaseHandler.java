package com.louis.shippingcalculator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.louis.shippingcalculator.util.DbUtil;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, DbUtil.DATABASE_NAME, null, DbUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ADDRESS_TABLE = "CREATE TABLE " + DbUtil.Address.TABLE_NAME + "(" +
                DbUtil.Address.KEY_ID + " INTEGER PRIMARY KEY," +
                DbUtil.Address.KEY_NAME + " TEXT," +
                DbUtil.Address.KEY_ADDRESS + " TEXT," +
                DbUtil.Address.KEY_CITY + " TEXT," +
                DbUtil.Address.KEY_PROV + " TEXT," +
                DbUtil.Address.KEY_POSTALCODE + " TEXT," +
                DbUtil.Address.KEY_COUNTRY + " TEXT)";

        String CREATE_BOX_TABLE = "CREATE TABLE " + DbUtil.Box.TABLE_NAME + "(" +
                DbUtil.Box.KEY_ID + " INTEGER PRIMARY KEY," +
                DbUtil.Box.KEY_NAME + " TEXT," +
                DbUtil.Box.KEY_WIDTH + " REAL," +
                DbUtil.Box.KEY_HEIGHT + " REAL," +
                DbUtil.Box.KEY_DEPTH + " REAL)";

        sqLiteDatabase.execSQL(CREATE_ADDRESS_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOX_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_ADDRESS_TABLE = "DROP TABLE IF EXISTS " + DbUtil.Address.TABLE_NAME;
        String DROP_BOX_TABLE = "DROP TABLE IF EXISTS " + DbUtil.Box.TABLE_NAME;

        sqLiteDatabase.execSQL(DROP_ADDRESS_TABLE);
        sqLiteDatabase.execSQL(DROP_BOX_TABLE);

        onCreate(sqLiteDatabase);
    }
}

package com.louis.shippingcalculator.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.util.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class AddressDA extends DatabaseHandler {
    public AddressDA(@Nullable Context context) {
        super(context);
    }

    public void add(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbUtil.Address.KEY_NAME, address.getName());
        values.put(DbUtil.Address.KEY_ADDRESS, address.getAddress());
        values.put(DbUtil.Address.KEY_CITY, address.getCity());
        values.put(DbUtil.Address.KEY_PROV, address.getProvince());
        values.put(DbUtil.Address.KEY_POSTALCODE, address.getPostalCode());
        values.put(DbUtil.Address.KEY_COUNTRY, address.getCountry());

        db.insert(DbUtil.Address.TABLE_NAME, null, values);
        db.close();
    }

    public Address getAddress(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DbUtil.Address.TABLE_NAME,
                new String[]{DbUtil.Address.KEY_ID, DbUtil.Address.KEY_NAME, DbUtil.Address.KEY_ADDRESS, DbUtil.Address.KEY_CITY, DbUtil.Address.KEY_PROV, DbUtil.Address.KEY_POSTALCODE, DbUtil.Address.KEY_COUNTRY},
                DbUtil.Address.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Address address = new Address(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            db.close();

            return address;
        }
        db.close();

        return null;
    }

    public List<Address> getAllAddress() {
        SQLiteDatabase db = this.getReadableDatabase();

        String SQL = "SELECT * FROM " + DbUtil.Address.TABLE_NAME;

        Cursor cursor = db.rawQuery(SQL, null);

        List<Address> boxList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Address address = new Address(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );

                boxList.add(address);
            } while (cursor.moveToNext());
        }
        db.close();

        return boxList;
    }

    public void deleteAddress(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DbUtil.Address.TABLE_NAME, DbUtil.Address.KEY_ID + "=?",
                new String[]{Integer.toString(id)});

        db.close();
    }
}

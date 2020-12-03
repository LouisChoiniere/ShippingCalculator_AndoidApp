package com.louis.shippingcalculator.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.model.Box;
import com.louis.shippingcalculator.util.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class BoxDA extends DatabaseHandler {
    public BoxDA(@Nullable Context context) {
        super(context);
    }

    public void add(Box box) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbUtil.Box.KEY_NAME, box.getName());
        values.put(DbUtil.Box.KEY_WIDTH, box.getWidth());
        values.put(DbUtil.Box.KEY_HEIGHT, box.getHeight());
        values.put(DbUtil.Box.KEY_DEPTH, box.getDepth());

        db.insert(DbUtil.Box.TABLE_NAME, null, values);
        db.close();
    }

    public Box getBox(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DbUtil.Box.TABLE_NAME,
                new String[]{DbUtil.Box.KEY_ID, DbUtil.Box.KEY_WIDTH, DbUtil.Box.KEY_HEIGHT, DbUtil.Box.KEY_DEPTH},
                DbUtil.Box.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Box box = new Box(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4)
            );
            db.close();

            return box;
        }
        db.close();

        return null;
    }

    public List<Box> getAllBox() {
        SQLiteDatabase db = this.getReadableDatabase();

        String SQL = "SELECT * FROM " + DbUtil.Box.TABLE_NAME;

        Cursor cursor = db.rawQuery(SQL, null);

        List<Box> boxList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Box box = new Box(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4)
                );

                boxList.add(box);
            } while (cursor.moveToNext());
        }
        db.close();

        return boxList;
    }

    public void deleteBox(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DbUtil.Box.TABLE_NAME, DbUtil.Box.KEY_ID + "=?",
                new String[]{Integer.toString(id)});

        db.close();
    }

    public void updateBox(Box box) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbUtil.Box.KEY_NAME, box.getName());
        values.put(DbUtil.Box.KEY_WIDTH, box.getWidth());
        values.put(DbUtil.Box.KEY_HEIGHT, box.getHeight());
        values.put(DbUtil.Box.KEY_DEPTH, box.getDepth());

        db.update(DbUtil.Box.TABLE_NAME, values,
                DbUtil.Box.KEY_ID + "=?", new String[]{String.valueOf(box.getId())});

        db.close();
    }
}

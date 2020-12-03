package com.louis.shippingcalculator.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.gson.Gson;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.model.Box;
import com.louis.shippingcalculator.ui.box.InfoBox;

public class BoxController {

    public static final String INTENT_BOX = "Box";

    public static void showInfo(Context context, Box box) {
        Intent intent = new Intent(context, InfoBox.class);
        intent.putExtra(INTENT_BOX, new Gson().toJson(box));
        context.startActivity(intent);
    }

    public static void launchAdd(Context context) {

    }

    public static void add(Context context, Box box) {
        BoxDA boxDA = new BoxDA(context);

        boxDA.add(box);
    }

    public static void delete(Context context, Box box) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Do you really want to this address?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new BoxDA(context).deleteBox(box.getId());
                        ((Activity) context).finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }


    public static void launchEdit(Context context, Box box) {

    }

    public static void edit(Context context, Box box) {
        BoxDA boxDA = new BoxDA(context);

        // ToDo implement edit box
//        boxDA.editBox(box);
    }
}

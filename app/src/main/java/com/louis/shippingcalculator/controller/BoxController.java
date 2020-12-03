package com.louis.shippingcalculator.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.gson.Gson;
import com.louis.shippingcalculator.ui.box.AddBox;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.model.Box;
import com.louis.shippingcalculator.ui.box.InfoBox;

public class BoxController {
    public static final int REQUEST_CODE_ADD = 11;

    public static final String INTENT_BOX = "Box";


    // Launch activity to show the info about the box
    public static void showInfo(Context context, Box box) {
        Intent intent = new Intent(context, InfoBox.class);
        intent.putExtra(INTENT_BOX, new Gson().toJson(box));
        context.startActivity(intent);
    }

    // Launch add activity for result
    public static void add(Context context) {
        Intent intent = new Intent(context, AddBox.class);
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    // Show prompt and delete box
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

    // result of start activity for result
    public static void activityResult(Context context, int requestCode, int resultCode, Intent intent) {

        switch (requestCode) {
            case REQUEST_CODE_ADD:
                new BoxDA(context).add(new Gson().fromJson(String.valueOf(intent.getStringExtra(BoxController.INTENT_BOX)), Box.class));
                break;
        }
    }
}

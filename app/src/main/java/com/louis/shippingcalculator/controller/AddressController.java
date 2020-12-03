package com.louis.shippingcalculator.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.gson.Gson;
import com.louis.shippingcalculator.ui.address.AddressForm;
import com.louis.shippingcalculator.ui.address.InfoAddress;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.model.Address;

public class AddressController {
    public static final String REQUEST_CODE = "requestCode";
    public static final int REQUEST_CODE_ADD = 21;
    public static final int REQUEST_CODE_EDIT = 22;

    public static final String INTENT_ADDRESS = "Addr";


    // Launch activity to show the info about the address
    public static void showInfo(Context context, Address address) {
        Intent intent = new Intent(context, InfoAddress.class);
        intent.putExtra(INTENT_ADDRESS, new Gson().toJson(address));
        context.startActivity(intent);
    }

    // Launch add activity for result
    public static void add(Context context) {
        Intent intent = new Intent(context, AddressForm.class);
        intent.putExtra(REQUEST_CODE, String.valueOf(REQUEST_CODE_ADD));
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    // Show prompt and delete address
    public static void delete(Context context, Address address) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Do you really want to this address?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new AddressDA(context).deleteAddress(address.getId());
                        ((Activity) context).finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public static void edit(Context context, Address address) {
        Intent intent = new Intent(context, AddressForm.class);
        intent.putExtra(REQUEST_CODE, String.valueOf(REQUEST_CODE_EDIT));
        intent.putExtra(INTENT_ADDRESS, new Gson().toJson(address));
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT);
    }


    // result of start activity for result
    public static void activityResult(Context context, int requestCode, int resultCode, Intent intent) {

        switch (requestCode) {
            case REQUEST_CODE_ADD:
                new AddressDA(context).add(new Gson().fromJson(String.valueOf(intent.getStringExtra(AddressController.INTENT_ADDRESS)), Address.class));
                break;
            case REQUEST_CODE_EDIT:
                new AddressDA(context).updateAddress(new Gson().fromJson(String.valueOf(intent.getStringExtra(AddressController.INTENT_ADDRESS)), Address.class));
                break;
        }
    }
}

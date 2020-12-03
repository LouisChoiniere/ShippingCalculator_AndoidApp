package com.louis.shippingcalculator.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.louis.shippingcalculator.ui.address.InfoAddress;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.model.Address;

public class AddressController {

    public static final String INTENT_ADDRESS = "Addr";

    public static void showInfo(Context context, Address address) {
        Intent intent = new Intent(context, InfoAddress.class);
        intent.putExtra(INTENT_ADDRESS, new Gson().toJson(address));
        context.startActivity(intent);
    }

    public static void launchAdd(Context context) {

    }

    public static void add(Context context, Address address) {
        AddressDA addressDA = new AddressDA(context);

        addressDA.add(address);
    }

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

    public static void launchEdit(Context context, Address address) {

    }

    public static void edit(Context context, Address address) {
        AddressDA addressDA = new AddressDA(context);

        // ToDo implement edit address
//        addressDA.editAddress(address);
    }
}

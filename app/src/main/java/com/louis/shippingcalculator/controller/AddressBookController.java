package com.louis.shippingcalculator.controller;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.louis.shippingcalculator.InfoAddress;
import com.louis.shippingcalculator.model.Address;

public class AddressBookController {

    public static final String INTENT_ADDRESS = "Addr";

    public static void showInfo(Context context, Address address) {
        Intent intent = new Intent(context, InfoAddress.class);
        intent.putExtra(INTENT_ADDRESS, new Gson().toJson(address));
        context.startActivity(intent);
    }
}

package com.louis.shippingcalculator.ui.address;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.AddressController;
import com.louis.shippingcalculator.model.Address;

public class InfoAddress extends AppCompatActivity {

    Address address;

    TextView nameTV;
    TextView addressTV;
    TextView cityTV;
    TextView provinceTV;
    TextView postalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_address);

        address = new Gson().fromJson(String.valueOf(getIntent().getStringExtra(AddressController.INTENT_ADDRESS)), Address.class);

        nameTV = findViewById(R.id.name);
        addressTV = findViewById(R.id.address);
        cityTV = findViewById(R.id.city);
        provinceTV = findViewById(R.id.province);
        postalCode = findViewById(R.id.postalCode);

        updateText();

        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressController.delete(InfoAddress.this, address);
            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressController.edit(InfoAddress.this, address);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == AddressController.REQUEST_CODE_EDIT) {
                address = new Gson().fromJson(String.valueOf(intent.getStringExtra(AddressController.INTENT_ADDRESS)), Address.class);
                updateText();
            }

            AddressController.activityResult(this, requestCode, resultCode, intent);
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void updateText() {
        nameTV.setText(address.getName());
        addressTV.setText(address.getAddress());
        cityTV.setText(address.getCity());
        provinceTV.setText(address.getProvince());
        postalCode.setText(address.getPostalCode());
    }
}
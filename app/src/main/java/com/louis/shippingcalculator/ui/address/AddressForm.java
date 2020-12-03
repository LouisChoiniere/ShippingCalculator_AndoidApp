package com.louis.shippingcalculator.ui.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.AddressController;
import com.louis.shippingcalculator.model.Address;

public class AddressForm extends AppCompatActivity {

    Address address;

    TextView titleTV;
    EditText nameET;
    EditText addressET;
    EditText cityET;
    EditText provinceET;
    EditText postalCodeET;
    EditText countryET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_address);

        Intent intent = getIntent();
        int REQUEST_CODE = Integer.parseInt(intent.getStringExtra(AddressController.REQUEST_CODE));

        titleTV = findViewById(R.id.title);
        nameET = findViewById(R.id.name);
        addressET = findViewById(R.id.address);
        cityET = findViewById(R.id.city);
        provinceET = findViewById(R.id.province);
        postalCodeET = findViewById(R.id.postalCode);
        countryET = findViewById(R.id.country);

        switch (REQUEST_CODE) {
            case AddressController.REQUEST_CODE_ADD:
                titleTV.setText("Add new Address");
                break;
            case AddressController.REQUEST_CODE_EDIT:
                address = new Gson().fromJson(String.valueOf(intent.getStringExtra(AddressController.INTENT_ADDRESS)), Address.class);

                titleTV.setText("Edit Address");
                nameET.setText(address.getName());
                addressET.setText(address.getAddress());
                cityET.setText(address.getCity());
                provinceET.setText(address.getProvince());
                postalCodeET.setText(address.getPostalCode());
                countryET.setText(address.getCountry());
                break;
        }

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address newAddress = new Address(nameET.getText().toString(), addressET.getText().toString(), cityET.getText().toString(), provinceET.getText().toString(), postalCodeET.getText().toString(), countryET.getText().toString());

                if(REQUEST_CODE == AddressController.REQUEST_CODE_EDIT)
                    newAddress.setId(address.getId());

                Intent intent = getIntent();
                intent.putExtra(AddressController.INTENT_ADDRESS, new Gson().toJson(newAddress));

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
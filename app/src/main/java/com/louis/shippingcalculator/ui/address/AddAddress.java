package com.louis.shippingcalculator.ui.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.BoxController;
import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.model.Box;

public class AddAddress extends AppCompatActivity {

    EditText nameET;
    EditText addressET;
    EditText cityET;
    EditText provinceET;
    EditText postalCodeET;
    EditText countryET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);


        nameET = findViewById(R.id.name);
        addressET = findViewById(R.id.address);
        cityET = findViewById(R.id.city);
        provinceET = findViewById(R.id.province);
        postalCodeET = findViewById(R.id.postalCode);
        countryET = findViewById(R.id.country);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Address address = new Address(nameET.getText().toString(), addressET.getText().toString(), cityET.getText().toString(), provinceET.getText().toString(), postalCodeET.getText().toString(), countryET.getText().toString());

                Intent intent = getIntent();
                intent.putExtra(BoxController.INTENT_BOX, new Gson().toJson(address));

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
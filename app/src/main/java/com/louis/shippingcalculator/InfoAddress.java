package com.louis.shippingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoAddress extends AppCompatActivity {

    TextView nameTV;
    TextView addressTV;
    TextView cityTV;
    TextView provinceTV;
    TextView postalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_address);


        nameTV = findViewById(R.id.name);
        addressTV = findViewById(R.id.address);
        cityTV = findViewById(R.id.city);
        provinceTV = findViewById(R.id.province);
        postalCode = findViewById(R.id.postalCode);

        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
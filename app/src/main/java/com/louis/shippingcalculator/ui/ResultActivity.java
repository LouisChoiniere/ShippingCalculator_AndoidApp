package com.louis.shippingcalculator.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.model.PriceQuote;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTV;
    private Button closeBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        List<PriceQuote> priceQuotes = new Gson().fromJson(String.valueOf(getIntent().getStringExtra("result")), new TypeToken<List<PriceQuote>>() {
        }.getType());

        String str = "";
        for (PriceQuote priceQuote : priceQuotes) {
            str += priceQuote.display() + "\n";
        }

        ((TextView)findViewById(R.id.results)).setText(str);
        ((Button)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
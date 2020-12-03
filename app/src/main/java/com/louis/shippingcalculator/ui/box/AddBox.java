package com.louis.shippingcalculator.ui.box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.BoxController;
import com.louis.shippingcalculator.model.Box;
import com.louis.shippingcalculator.ui.box.InfoBox;

public class AddBox extends AppCompatActivity {

    EditText nameET;
    EditText widthET;
    EditText heightET;
    EditText depthET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_box);

        nameET = findViewById(R.id.name);
        widthET = findViewById(R.id.width);
        heightET = findViewById(R.id.height);
        depthET = findViewById(R.id.depth);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Box box = new Box(nameET.getText().toString(), Double.parseDouble(widthET.getText().toString()), Double.parseDouble(heightET.getText().toString()), Double.parseDouble(depthET.getText().toString()));

                Intent intent = getIntent();
                intent.putExtra(BoxController.INTENT_BOX, new Gson().toJson(box));

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
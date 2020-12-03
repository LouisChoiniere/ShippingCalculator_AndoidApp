package com.louis.shippingcalculator.ui.box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.BoxController;
import com.louis.shippingcalculator.model.Box;

public class BoxForm extends AppCompatActivity {

    Box box;

    TextView titleTV;
    EditText nameET;
    EditText widthET;
    EditText heightET;
    EditText depthET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_box);

        Intent intent = getIntent();
        int REQUEST_CODE = Integer.parseInt(intent.getStringExtra(BoxController.REQUEST_CODE));

        titleTV = findViewById(R.id.title);
        nameET = findViewById(R.id.name);
        widthET = findViewById(R.id.width);
        heightET = findViewById(R.id.height);
        depthET = findViewById(R.id.depth);

        switch (REQUEST_CODE) {
            case BoxController.REQUEST_CODE_ADD:
                titleTV.setText("Add new box");
                break;
            case BoxController.REQUEST_CODE_EDIT:
                box = new Gson().fromJson(String.valueOf(intent.getStringExtra(BoxController.INTENT_BOX)), Box.class);

                titleTV.setText("Edit box");
                nameET.setText(box.getName());
                widthET.setText(String.valueOf(box.getWidth()));
                heightET.setText(String.valueOf(box.getHeight()));
                depthET.setText(String.valueOf(box.getDepth()));
                break;
        }


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Box newBox = new Box(nameET.getText().toString(), Double.parseDouble(widthET.getText().toString()), Double.parseDouble(heightET.getText().toString()), Double.parseDouble(depthET.getText().toString()));

                if(REQUEST_CODE == BoxController.REQUEST_CODE_EDIT)
                    newBox.setId(box.getId());

                Intent intent = getIntent();
                intent.putExtra(BoxController.INTENT_BOX, new Gson().toJson(newBox));

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
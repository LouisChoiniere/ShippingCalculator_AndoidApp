package com.louis.shippingcalculator.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.PriceCalculatorController;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.model.Address;
import com.louis.shippingcalculator.model.Box;
import com.louis.shippingcalculator.model.PriceCalculator;
import com.louis.shippingcalculator.util.AsyncResponse;
import com.louis.shippingcalculator.util.ResultActivity;

import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private String TAG = "HomeFragment";

    private Context context;

    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Spinner boxSpinner;
    private EditText weightET;
    private Button button;

    private Address from;
    private Address to;
    private Box box;
    private Double weight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fromSpinner = view.findViewById(R.id.from);
        toSpinner = view.findViewById(R.id.to);
        boxSpinner = view.findViewById(R.id.box);
        weightET = view.findViewById(R.id.weight);
        button = view.findViewById(R.id.submit);

        button.setOnClickListener(this);

        // Lists of addresses and boxes (names only)
        List<AddressSpinner> addressSpinnerList = new AddressDA(getContext()).getAllAddress().stream().map(o -> new AddressSpinner(o)).collect(Collectors.toList());
        List<BoxSpinner> boxSpinnerList = new BoxDA(getContext()).getAllBox().stream().map(o -> new BoxSpinner(o)).collect(Collectors.toList());

        // setup dataAdapters
        ArrayAdapter<AddressSpinner> addressDataAdapter = new ArrayAdapter<AddressSpinner>(getContext(), android.R.layout.simple_spinner_item, addressSpinnerList);
        addressDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(addressDataAdapter);
        toSpinner.setAdapter(addressDataAdapter);

        ArrayAdapter<BoxSpinner> boxDataAdapter = new ArrayAdapter<BoxSpinner>(getContext(), android.R.layout.simple_spinner_item, boxSpinnerList);
        boxDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boxSpinner.setAdapter(boxDataAdapter);

        // Set listener
        fromSpinner.setOnItemSelectedListener(this);
        toSpinner.setOnItemSelectedListener(this);
        boxSpinner.setOnItemSelectedListener(this);

        AddressDA addressDA = new AddressDA(getContext());
        BoxDA boxDA = new BoxDA(getContext());


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        Log.d(TAG, "onItemSelected: " + parent.getSelectedItem().toString());

        switch (parent.getId()) {
            case R.id.from:
                this.from = new Address((Address) parent.getSelectedItem());
                break;
            case R.id.to:
                this.to = new Address((Address) parent.getSelectedItem());
                break;
            case R.id.box:
                this.box = new Box((Box) parent.getSelectedItem());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()) {
            case R.id.from:
                this.from = null;
                break;
            case R.id.to:
                this.to = null;
                break;
            case R.id.box:
                this.box = null;
                break;
        }
    }

    @Override
    public void onClick(View view) {

        try {
            if (this.from == null || this.to == null || this.box == null || weightET.getText().toString().isEmpty())
                throw new NoSuchFieldException("Missing field");

            PriceCalculator priceCalculator = new PriceCalculator(this.from, this.to, this.box, 1);

            new PriceCalculatorController(getContext()).getShippingPrice(priceCalculator, new AsyncResponse() {
                @Override
                public void onFinished(Object object) {
                    Log.d(TAG, "onFinished: " + object.getClass().toString());

                    Intent intent = new Intent(getContext(), ResultActivity.class);
                    intent.putExtra("result", new Gson().toJson(object));
                    getContext().startActivity(intent);
                }
            });
        } catch (NoSuchFieldException e) {
            new AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage("Make sure no fields are empty")
                    .setIcon(android.R.drawable.stat_sys_warning)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    }).show();
        } catch (Exception e) {
            new AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage("An error occurred")
                    .setIcon(android.R.drawable.stat_sys_warning)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    }).show();
        }
    }
}
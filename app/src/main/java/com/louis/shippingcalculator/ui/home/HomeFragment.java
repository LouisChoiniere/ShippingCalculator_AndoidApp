package com.louis.shippingcalculator.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.PriceCalculatorController;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.model.PriceCalculator;
import com.louis.shippingcalculator.util.AsyncResponse;

public class HomeFragment extends Fragment {
    private String TAG = "HomeFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        AddressDA addressDA = new AddressDA(getContext());
        BoxDA boxDA = new BoxDA(getContext());

        PriceCalculator priceCalculator = new PriceCalculator(addressDA.getAddress(1), addressDA.getAddress(2), boxDA.getBox(1), 1);

        new PriceCalculatorController(getContext()).getShippingPrice(priceCalculator, new AsyncResponse() {
            @Override
            public void onFinished(Object object) {
                Log.d(TAG, "onFinished: " + object.getClass().toString());
            }
        });

        return view;
    }
}
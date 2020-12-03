package com.louis.shippingcalculator.ui.address;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.model.Address;

import java.util.List;

public class AddressFragment extends Fragment {

    public static final String TAG = "AddressFragment";

    AddressDA db;

    private List<Address> addressList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new AddressDA(this.getContext());

        addressList = db.getAllAddress();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        // Setup recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        AddressListAdapter adapter = new AddressListAdapter(this.getContext(), addressList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Setup new Box
        view.findViewById(R.id.newAddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: New Address");
            }
        });

        return view;
    }
}
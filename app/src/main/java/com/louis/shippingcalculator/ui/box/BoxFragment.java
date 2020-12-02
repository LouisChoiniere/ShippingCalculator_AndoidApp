package com.louis.shippingcalculator.ui.box;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.louis.shippingcalculator.AddressListAdapter;
import com.louis.shippingcalculator.BoxListAdapter;
import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.data.AddressDA;
import com.louis.shippingcalculator.data.BoxDA;
import com.louis.shippingcalculator.model.Box;

import java.util.List;

public class BoxFragment extends Fragment {

    public static final String TAG = "BoxFragment";

    BoxDA db;

    private List<Box> boxList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new BoxDA(this.getContext());

        boxList = db.getAllBox();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_box, container, false);

        // Setup recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        BoxListAdapter adapter = new BoxListAdapter(this.getContext(), boxList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // Setup new Box
        view.findViewById(R.id.newBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: New Box");
            }
        });

        return view;
    }
}
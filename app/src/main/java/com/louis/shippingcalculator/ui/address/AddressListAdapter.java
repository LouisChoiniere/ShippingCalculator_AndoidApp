package com.louis.shippingcalculator.ui.address;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.louis.shippingcalculator.R;
import com.louis.shippingcalculator.controller.AddressController;
import com.louis.shippingcalculator.model.Address;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    public static final String TAG = "AddressListAdapter";

    private Context context;
    private List<Address> addressList;

    public AddressListAdapter(Context context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_address, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address address = addressList.get(position);

        holder.nameTV.setText(address.getName());
        holder.addressTV.setText(address.getAddress() + " " + address.getCity() + " " + address.getPostalCode());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + address.toString());
                AddressController.showInfo(context, address);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView nameTV;
        TextView addressTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
            nameTV = itemView.findViewById(R.id.name);
            addressTV = itemView.findViewById(R.id.address);
        }
    }
}

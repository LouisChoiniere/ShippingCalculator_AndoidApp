package com.louis.shippingcalculator.ui.home;

import com.louis.shippingcalculator.model.Address;

public class AddressSpinner extends Address {

    public AddressSpinner(Address address) {
        super(address);
    }

    @Override
    public String toString() {
        return super.getName() + "  " + super.getPostalCode();
    }
}

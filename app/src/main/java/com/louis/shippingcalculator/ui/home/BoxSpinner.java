package com.louis.shippingcalculator.ui.home;

import com.louis.shippingcalculator.model.Box;

public class BoxSpinner extends Box {

    public BoxSpinner(Box box) {
        super(box);
    }

    @Override
    public String toString() {
        return getName();
    }
}

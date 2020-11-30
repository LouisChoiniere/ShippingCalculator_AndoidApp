package com.louis.shippingcalculator.model;

public class Box {
    private int id;
    private double width;
    private double height;
    private double depth;

    public Box() {
    }

    public Box(int id, double width, double height, double depth) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }
}

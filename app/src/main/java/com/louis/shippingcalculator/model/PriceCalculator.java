package com.louis.shippingcalculator.model;

public class PriceCalculator {

    private Address from;
    private Address to;
    private Box box;
    private double weight;
    private double price;

    public PriceCalculator() {
    }

    public PriceCalculator(Address from, Address to, Box box, double weight) {
        this.from = from;
        this.to = to;
        this.box = box;
        this.weight = weight;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    public Box getBox() {
        return box;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }
}

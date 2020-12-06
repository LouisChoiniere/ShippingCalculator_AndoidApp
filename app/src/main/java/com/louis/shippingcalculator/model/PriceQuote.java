package com.louis.shippingcalculator.model;

public class PriceQuote {

    private String name;
    private String price;

    public PriceQuote() {
    }

    public PriceQuote(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String diplay() {
        return name + ": " + price;
    }

    @Override
    public String toString() {
        return "PriceQuotes{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

package com.louis.shippingcalculator.model;

public class Address {
    private int id;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Address() {
    }

    public Address(int id, String address, String city, String province, String postalCode, String country) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

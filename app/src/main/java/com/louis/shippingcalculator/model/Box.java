package com.louis.shippingcalculator.model;

public class Box {
    private int id;
    private String name;
    private double width;
    private double height;
    private double depth;

    public Box() {
    }

    public Box(Box box) {
        this.name = box.name;
        this.width = box.width;
        this.height = box.height;
        this.depth = box.depth;
    }

    public Box(String name, double width, double height, double depth) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public Box(int id, String name, double width, double height, double depth) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}

package com.example.finalchopchop_recipeapp;

public class Summary {

    private String name;
    private String price;
    int index;


    public Summary(String name, String price, int index) {
        this.name = name;
        this.price = price;
        this.index=index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

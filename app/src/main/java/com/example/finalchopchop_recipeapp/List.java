package com.example.finalchopchop_recipeapp;

public class List {

    private String name;
    private int price;


    public List(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public List(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

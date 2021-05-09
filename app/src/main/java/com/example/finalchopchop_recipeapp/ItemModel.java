package com.example.finalchopchop_recipeapp;

public class ItemModel {

    private String item;
    private int price;

    public ItemModel() {
    }

    public ItemModel(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

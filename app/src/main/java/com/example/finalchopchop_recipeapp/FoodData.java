package com.example.finalchopchop_recipeapp;

public class FoodData {

    private String itemName;
    private String itemIngredients;
    private String itemMethod;
    private String itemImage;
    private String key;

    public FoodData() {
    }

    public FoodData(String itemName, String itemIngredients, String itemMethod, String itemImage) {
        this.itemName = itemName;
        this.itemIngredients = itemIngredients;
        this.itemMethod = itemMethod;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemIngredients() {
        return itemIngredients;
    }

    public String getItemMethod() {
        return itemMethod;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

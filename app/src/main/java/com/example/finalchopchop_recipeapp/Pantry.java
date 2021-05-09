package com.example.finalchopchop_recipeapp;

public class Pantry {

    private String item;

    private String size;
    private String date;

    private String notificationTime;
    public  String dayDifference;



    public Pantry(String item, String date, String size, String notificationTime, String dayDifference) {

        this.item = item;
        this.date = date;
        this.size = size;
        this.notificationTime = notificationTime;
        this.dayDifference = dayDifference;


    }

    public Pantry() {
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }


    public String getDayDifference() {
        return dayDifference;
    }

    public void setDayDifference(String dayDifference) {
        this.dayDifference = dayDifference;
    }

}

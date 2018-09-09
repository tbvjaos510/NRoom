package com.nroom.data;

import android.graphics.drawable.Drawable;

import java.util.List;

public class HouseItem {

    private static String test;
    private Drawable image;
    private String price;
    private String location;
    private String notice;

    public HouseItem(String test){
        HouseItem.test = test;
    }
    public static String getTest() { return test; }
    //public static void setTest(String test) { this.test = test ; }


    public HouseItem(Drawable image, String price, String location, String notice) {
        this.image = image;
        this.price = price;
        this.location = location;
        this.notice = notice;
    }
    public  HouseItem(){

    }

    public Drawable getImage(){
        return image;
    }

    public void setImage(Drawable image){
        this.image = image;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}

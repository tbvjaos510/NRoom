package com.nroom.listview;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private Drawable icon;
    private String price;
    private String location;
    private String notice;

    public Drawable getIcon(){
        return icon;
    }

    public void setIcon(Drawable icon){
        this.icon = icon;
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

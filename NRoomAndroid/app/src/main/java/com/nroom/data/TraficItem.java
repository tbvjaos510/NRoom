package com.nroom.data;

public class TraficItem {

    private double lat;
    private double lng;
    private String nodenm;

    public TraficItem(double lat, double lng, String nodenm) {
        this.lat = lat;
        this.lng = lng;
        this.nodenm = nodenm;
    }

    public void setLat(double lat) {

        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setNodenm(String nodenm) {
        this.nodenm = nodenm;
    }

    public double getLat() {

        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getNodenm() {
        return nodenm;
    }
}

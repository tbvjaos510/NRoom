package com.nroom.data;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MapClusterItem implements ClusterItem {

    private LatLng position;
    private String title;
    private String snippet;

    public MapClusterItem(double lat, double lng) {
        this.position = new LatLng(lat, lng);
    }

    public MapClusterItem(double lat, double lng, String title) {
        this.position = new LatLng(lat, lng);
        this.title = title;
    }

    public MapClusterItem(double lat, double lng, String title, String snippet) {
        this.position = new LatLng(lat, lng);
        this.title = title;
        this.snippet = snippet;
    }

    public MapClusterItem(LatLng position) {
        this.position = position;
    }

    public MapClusterItem(LatLng position, String title) {
        this.position = position;
        this.title = title;
    }

    public MapClusterItem(LatLng position, String title, String snippet) {
        this.position = position;
        this.title = title;
        this.snippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }
}

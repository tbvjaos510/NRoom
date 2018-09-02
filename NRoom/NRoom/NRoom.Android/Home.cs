using System;
using Android.Gms.Maps.Model;
using Com.Google.Maps.Android.Clustering;

namespace NRoom.Droid
{
    internal class Home : Java.Lang.Object, IClusterItem
    {
        public LatLng Position { get; set; }

        public string Snippet { get; set; }

        public string Title { get; set; }

        public Home(double lat, double lng)
        {
            Position = new LatLng(lat, lng);
        }
        public Home(LatLng location)
        {
            Position = location;
        }
        public Home(double lat, double lng, string title, string snippet)
        {
            Position = new LatLng(lat, lng);
            Title = title;
            Snippet = snippet;
        }
    }
}


/*
 public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;

    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public MyItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
     */

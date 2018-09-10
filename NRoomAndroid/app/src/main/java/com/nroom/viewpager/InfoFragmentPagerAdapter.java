package com.nroom.viewpager;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class InfoFragmentPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final double lng;
    private final double lat;


    public InfoFragmentPagerAdapter(FragmentManager fm, Context context, double lng, double lat) {
        super(fm);
        this.context = context;
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

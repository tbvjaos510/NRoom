package com.nroom.viewpager;

import android.content.Context;
import android.os.Bundle;

import com.nroom.fragment.ChartFragment;
import com.nroom.fragment.TrafficFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class InfoFragmentPagerAdapter extends FragmentPagerAdapter {

    private final double lng;
    private final double lat;

    public InfoFragmentPagerAdapter(FragmentManager fm, double lng, double lat) {
        super(fm);
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChartFragment();
            case 1:
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", lat);
                bundle.putDouble("lng", lng);

                TrafficFragment trafficFragment = new TrafficFragment();
                trafficFragment.setArguments(bundle);
                return trafficFragment;
            case 2:
                return new Fragment();
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}

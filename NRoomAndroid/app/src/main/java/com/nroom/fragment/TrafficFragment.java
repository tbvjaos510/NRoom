package com.nroom.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nroom.R;
import com.nroom.data.StaticResources;
import com.nroom.data.TrafficItem;
import com.nroom.network.JSONTaskTraffic;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrafficFragment extends Fragment {

    private double lat;
    private double lng;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lat = getArguments().getDouble("lat");
        lng = getArguments().getDouble("lng");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_traffic_pager, container, false);

        TextView busStop = view.findViewById(R.id.bus_stop);
        TextView metroStation = view.findViewById(R.id.metro_station);

        new JSONTaskTraffic(getContext()).setTaskListener(trafficItems -> {
            if (trafficItems.isEmpty())
                busStop.setText("없음");
            else
                for (TrafficItem trafficItem : trafficItems) {
                    busStop.setText(trafficItem.getNodenm());
                }
        }).execute(StaticResources.HOST + "/api/busPos?lng=" + lng + "&lat=" + lat);

        return view;
    }
}

package com.nroom.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.nroom.R;
import com.nroom.data.TrafficItem;
import com.nroom.network.JSONTaskTraffic;

import java.util.ArrayList;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class InfoPagerAdapter extends PagerAdapter {

    private final Context context;
    private final double lng;
    private final double lat;


    public InfoPagerAdapter(Context context, double lng, double lat) {
        this.context = context;
        this.lng = lng;
        this.lat = lat;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view;
        switch (position) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.item_chart_pager, container, false);

                LineData data = makeDummy(12, 100);

                LineChart chart = view.findViewById(R.id.chart);
                ConstraintLayout constraintLayout = view.findViewById(R.id.current_not_support);
                container.addView(chart);
                container.addView(constraintLayout);

                setupChart(chart, data, Color.rgb(250, 104, 104));

                return chart;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_traffic_pager, container, false);

                TextView busStop = view.findViewById(R.id.bus_stop);
                TextView metroStation = view.findViewById(R.id.metro_station);

                new JSONTaskTraffic(context).setTaskListener(trafficItems -> {
                    for (TrafficItem trafficItem: trafficItems) {
                        busStop.setText(trafficItem.getNodenm());
                    }
                }).execute("http://10.80.161.54:80/api/busPos?lng=" + lng + "&lat=" + lat);

                //container.addView(constraintLayout);

                //return constraintLayout;
        }

        return container;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    private void setupChart(LineChart chart, LineData data, @ColorInt int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setPinchZoom(false);
        chart.setViewPortOffsets(10, 0, 10, 0);
        chart.setData(data);

        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(false);

        // animate calls invalidate()...
        chart.animateX(500);
    }

    private LineData makeDummy(int count, float range) {

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range) + 3;
            entries.add(new Entry(i, val));
        }

        // create a dataset and give it a type
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setCircleHoleRadius(2.5f);
        lineDataSet.setColor(Color.rgb(250, 104, 104));
        lineDataSet.setCircleColor(Color.rgb(250, 104, 104));
        lineDataSet.setHighLightColor(Color.rgb(250, 104, 104));
        lineDataSet.setDrawValues(false);

        // create a data object with the datasets

        return new LineData(lineDataSet);
    }
}

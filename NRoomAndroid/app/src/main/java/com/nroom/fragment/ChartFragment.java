package com.nroom.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.nroom.R;

import java.util.ArrayList;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

public class ChartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_chart_pager, container, false);

        LineData data = makeDummy(12, 100);

        LineChart chart = view.findViewById(R.id.chart);

        setupChart(chart, data, Color.rgb(250, 104, 104));

        return view;
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

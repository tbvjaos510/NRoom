package com.nroom.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.nroom.R;
import com.nroom.data.StaticResources;
import com.nroom.viewpager.ImagePagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import androidx.annotation.ColorInt;
import androidx.viewpager.widget.ViewPager;

public class DetailActivity extends BaseActivity {

    private ViewPager viewPager;
    private WormDotsIndicator wormDotsIndicator;
    private TextView summary;
    private TextView price;
    private TextView saleID;
    private TextView area;
    private TextView adminExp;
    private TextView structure;
    private LineChart chart;
    private TextView detailSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        bindViews();

    }

    private void initViews() {
        viewPager = findViewById(R.id.view_pager);
        wormDotsIndicator = findViewById(R.id.worm_dot);
        summary = findViewById(R.id.summary);
        price = findViewById(R.id.price);
        saleID = findViewById(R.id.sale_id);
        area = findViewById(R.id.area);
        adminExp = findViewById(R.id.admin_exp);
        structure = findViewById(R.id.structure);
        chart = findViewById(R.id.chart);
        detailSummary = findViewById(R.id.detail_summary);
    }

    private void bindViews() {
        Intent intent = getIntent();
        viewPager.setAdapter(new ImagePagerAdapter(this, intent.getIntArrayExtra("images")));
        wormDotsIndicator.setViewPager(viewPager);

        String structureData =  intent.getStringExtra("structure");
        if(structureData.equals("1")){
            structureData = "아파트";
        }else{
            structureData = "연립다세대";
        }
        String setAdminExp = intent.getStringExtra("admin_exp");
        if(!setAdminExp.equals("0")){
            setAdminExp = "없음";
        }

        summary.setText(intent.getStringExtra("summary"));
        price.setText(intent.getStringExtra("price"));
        saleID.setText(intent.getStringExtra("sale_id"));
        area.setText(intent.getStringExtra("area"));
        adminExp.setText(setAdminExp);
        structure.setText(structureData);
        detailSummary.setText(intent.getStringExtra("detail_summary"));

        LineData data = makeDummy(12, 100);

        setupChart(chart, data, Color.rgb(250, 104, 104));
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

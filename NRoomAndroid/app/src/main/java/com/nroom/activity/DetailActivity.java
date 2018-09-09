package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.nroom.R;

public class DetailActivity extends BaseActivity {

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

    private void bindViews() {
        Intent intent = getIntent();
        summary.setText(intent.getStringExtra("summary"));
        price.setText(intent.getStringExtra("price"));
        saleID.setText(intent.getStringExtra("sale_id"));
        area.setText(intent.getStringExtra("area"));
        adminExp.setText(intent.getStringExtra("admin_exp"));
        structure.setText(intent.getStringExtra("structure"));
        detailSummary.setText(intent.getStringExtra("detail_summary"));
    }

    private void initViews() {
        summary = findViewById(R.id.summary);
        price = findViewById(R.id.price);
        saleID = findViewById(R.id.sale_id);
        area = findViewById(R.id.area);
        adminExp = findViewById(R.id.admin_exp);
        structure = findViewById(R.id.structure);
        chart = findViewById(R.id.chart);
        detailSummary = findViewById(R.id.detail_summary);
    }
}

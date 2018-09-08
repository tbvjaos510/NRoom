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
        //bindViews();

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
        summary = bind(R.id.summary);
        price = bind(R.id.price);
        saleID = bind(R.id.sale_id);
        area = bind(R.id.area);
        adminExp = bind(R.id.admin_exp);
        structure = bind(R.id.structure);
        chart = bind(R.id.chart);
        detailSummary = bind(R.id.detail_summary);
    }
}

package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.nroom.R;
import com.nroom.viewpager.ImagePagerAdapter;
import com.nroom.viewpager.InfoFragmentPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;

public class DetailActivity extends BaseActivity {

    private ViewPager viewPagerToolbar;
    private WormDotsIndicator wormDotsIndicatorToolbar;
    private ViewPager viewPager;
    private WormDotsIndicator wormDotsIndicator;
    private TextView summary;
    private TextView price;
    private TextView saleID;
    private TextView area;
    private TextView adminExp;
    private TextView structure;
    private TextView detailSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        initViews();
        bindViews();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return false;
    }

    private void initViews() {
        viewPagerToolbar = findViewById(R.id.view_pager_toolbar);
        wormDotsIndicatorToolbar = findViewById(R.id.worm_dot_toolbar);
        viewPager = findViewById(R.id.view_pager);
        wormDotsIndicator = findViewById(R.id.worm_dot);
        summary = findViewById(R.id.summary);
        price = findViewById(R.id.price);
        saleID = findViewById(R.id.sale_id);
        area = findViewById(R.id.area);
        adminExp = findViewById(R.id.admin_exp);
        structure = findViewById(R.id.structure);
        detailSummary = findViewById(R.id.detail_summary);
    }

    private void bindViews() {
        Intent intent = getIntent();
        viewPagerToolbar.setAdapter(new ImagePagerAdapter(this, intent.getIntArrayExtra("images")));
        wormDotsIndicatorToolbar.setViewPager(viewPagerToolbar);

        setTitle(intent.getStringExtra("name"));

        String structureData = intent.getStringExtra("structure");
        if (structureData.equals("1")) {
            structureData = "아파트";
        } else {
            structureData = "연립다세대";
        }
        String setAdminExp = intent.getStringExtra("admin_exp");
        if (!setAdminExp.equals("0")) {
            setAdminExp = "없음";
        }

        summary.setText(intent.getStringExtra("summary"));
        price.setText(intent.getStringExtra("price"));
        saleID.setText(intent.getStringExtra("sale_id"));
        area.setText(intent.getStringExtra("area"));
        adminExp.setText(setAdminExp);
        structure.setText(structureData);
        detailSummary.setText(intent.getStringExtra("detail_summary"));


        viewPager.setAdapter(new InfoFragmentPagerAdapter(getSupportFragmentManager(), intent.getDoubleExtra("lng", 0), intent.getDoubleExtra("lat", 0)));
        wormDotsIndicator.setViewPager(viewPager);
    }
}

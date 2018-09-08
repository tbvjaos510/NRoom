package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nroom.R;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private  AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        Button btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        Button btnList = findViewById(R.id.btnList);
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        });

        MobileAds.initialize(this, "ca-app-pub-3072268700543290~9298958419");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}

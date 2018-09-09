package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nroom.BuildConfig;
import com.nroom.R;

public class MainActivity extends BaseActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        Button btnMap = bind(R.id.btnMap);
        btnMap.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        });

        Button btnList = bind(R.id.btnList);
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        });

        MobileAds.initialize(this, "ca-app-pub-3072268700543290~9298958419");

        mAdView = bind(R.id.adView);
        AdRequest.Builder builder = new AdRequest.Builder();
                if (BuildConfig.DEBUG) {
                    builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
                    builder.setRequestAgent("android_studio:ad_template");
                }
        mAdView.loadAd(builder.build());
    }
    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAdView.resume();
        super.onResume();
    }
}

package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

        findViewById(R.id.toolbar_layout).setStateListAnimator(null);
        findViewById(R.id.toolbar_layout).setBackgroundResource(android.R.color.transparent);
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

        MobileAds.initialize(this);

        mAdView = findViewById(R.id.adView);
        AdRequest.Builder builder = new AdRequest.Builder();
                if (BuildConfig.DEBUG) {
                    builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
                    builder.setRequestAgent("android_studio:ad_template");
                }
        mAdView.loadAd(builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //TODO Settings
                return true;
        }
        return false;
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

package com.nroom.activity;

import android.os.Bundle;

import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nroom.R;

import androidx.appcompat.app.AppCompatActivity;
public class MapActivity extends AppCompatActivity {

    // CLIENT ID
    public static final String CLIENT_ID = "qxSxM3dHkv_nQodnLE34";  //발급받은 Client ID

    // 네이버 맵 객체
    NMapView nMapView;

    // 맵 컨트롤러
    NMapController nMapController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setSupportActionBar(findViewById(R.id.toolbar));
        findViewById(R.id.toolbar_layout).setBackgroundResource(R.color.transparent_dark);
    }


}

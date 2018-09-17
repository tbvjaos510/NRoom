package com.nroom.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.nroom.R;
import com.nroom.data.HouseItem;
import com.nroom.data.MapClusterItem;
import com.nroom.data.StaticResources;
import com.nroom.network.JSONTaskAll;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private ClusterManager<MapClusterItem> mapClusterItemClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        findViewById(R.id.toolbar_layout).setStateListAnimator(null);
        findViewById(R.id.toolbar_layout).setBackgroundResource(R.color.transparent_dark);
        setSupportActionBar(findViewById(R.id.toolbar));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // 기본 표시 지점을 대한민국 가운데로 설정
        Geocoder geocoder = new Geocoder(this);
        try {
            Address address = geocoder.getFromLocationName(Locale.KOREA.getDisplayName(), 1).get(0);
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(address.getLatitude(), address.getLongitude()), 7));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setUpCluster();
        addMarker();
    }

    private void setUpCluster() {
        mapClusterItemClusterManager = new ClusterManager<>(this, googleMap);

        googleMap.setOnCameraIdleListener(mapClusterItemClusterManager);
        googleMap.setOnMarkerClickListener(mapClusterItemClusterManager);
    }

    private void addMarker() {
        new JSONTaskAll(this).setTaskListener(houseList -> {
            for (HouseItem item : houseList) {
                mapClusterItemClusterManager.addItem(new MapClusterItem(item.get위도(), item.get경도(), item.get건물명()));
                googleMap.setOnInfoWindowClickListener(marker -> {
                    HouseItem houseItem = StaticResources.findHouseItemByLatLng(houseList, marker.getPosition().latitude, marker.getPosition().longitude);

                    Intent intent = new Intent(this, DetailActivity.class);
                    intent.putExtra("images", StaticResources.getImages(new Random().nextInt(5)));
                    intent.putExtra("summary", "요약");
                    intent.putExtra("admin_exp", "5만");
                    intent.putExtra("detail_summary", "☆★회사 다니시는 분들 주목★☆\n이 위치, 이 금액 실화냐?!\n\n■널찍한 공간, 수납공간도 다수입니다!\n\n■빌트인 냉장고부터 건조기까지 완전 풀옵션~★\n\n■최고급 소재 사용으로 환경 호르몬 걱정은 안녕~~\n\n■관리비 걱정 없이 생활을 만끽하세요~");
                    intent.putExtra("lat", marker.getPosition().latitude);
                    intent.putExtra("lng", marker.getPosition().longitude);
                    if (houseItem != null) {
                        intent.putExtra("price", StaticResources.getPrice(houseItem, this));
                        intent.putExtra("sale_id", houseItem.getId());
                        intent.putExtra("name", houseItem.get건물명());
                        intent.putExtra("area", houseItem.get전용면적());
                        intent.putExtra("structure", houseItem.get집종류());
                    }


                    startActivity(intent);
                });
            }
        }).execute(StaticResources.HOST + "/api/Alltrade");
    }
}

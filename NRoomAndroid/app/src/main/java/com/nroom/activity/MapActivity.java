package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;
import com.nroom.R;
import com.nroom.data.HouseItem;
import com.nroom.network.JSONTask;
import com.nroom.network.JSONTaskAll;
import com.nroom.nmap.NMapPOIFlagType;
import com.nroom.nmap.NMapViewerResourceProvider;

public class MapActivity extends BaseActivity
        implements NMapView.OnMapStateChangeListener {

    private static final String CLIENT_ID = "qxSxM3dHkv_nQodnLE34";// 애플리케이션 클라이언트 아이디 값

    private NMapView nMapView;
    private NMapContext nMapContext;
    private NMapController nMapController;
    private FloatingActionButton zoomInFAB;
    private FloatingActionButton zoomOutFAB;
    private NMapResourceProvider nMapViewerResourceProvider;
    private NMapOverlayManager nOverlayManager;
    private NMapLocationManager nMapLocationManager;
    private NMapCompassManager nMapCompassManager;
    private NMapMyLocationOverlay nMapMyLocationOverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        findViewById(R.id.toolbar_layout).setStateListAnimator(null);
        findViewById(R.id.toolbar_layout).setBackgroundResource(R.color.transparent_dark);
        setSupportActionBar(findViewById(R.id.toolbar));

        nMapContext = new NMapContext(this);
        nMapContext.onCreate();

        nMapView = findViewById(R.id.n_map);

        zoomInFAB = findViewById(R.id.zoom_in_fab);
        zoomOutFAB = findViewById(R.id.zoom_out_fab);
        nMapContext.setupMapView(nMapView);

        nMapController = nMapView.getMapController();

        nMapView.setClientId(CLIENT_ID);
        nMapView.setOnMapStateChangeListener(this);

        zoomInFAB.setOnClickListener(v -> {
            nMapController.zoomIn();
            updateZoomFAB();
        });
        zoomOutFAB.setOnClickListener(v -> {
            nMapController.zoomOut();
            updateZoomFAB();
        });

        // create resource provider
        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        // create overlay manager
        nOverlayManager = new NMapOverlayManager(this, nMapView, nMapViewerResourceProvider);

        // location manager
        nMapLocationManager = new NMapLocationManager(this);
        nMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

        // compass manager
        nMapCompassManager = new NMapCompassManager(this);

        // create my location overlay
        nMapMyLocationOverlay = nOverlayManager.createMyLocationOverlay(nMapLocationManager, nMapCompassManager);

        //TODO
        addMarker();

        startMyLocation();
    }

    private void startMyLocation() {

        if (nMapMyLocationOverlay != null) {
            if (!nOverlayManager.hasOverlay(nMapMyLocationOverlay)) {
                nOverlayManager.addOverlay(nMapMyLocationOverlay);
            }

            if (nMapLocationManager.isMyLocationEnabled()) {

                if (!nMapView.isAutoRotateEnabled()) {
                    nMapMyLocationOverlay.setCompassHeadingVisible(true);

                    nMapCompassManager.enableCompass();

                    nMapView.setAutoRotateEnabled(true, false);
                } else {
                    stopMyLocation();
                }

                nMapView.postInvalidate();
            } else {
                boolean isMyLocationEnabled = nMapLocationManager.enableMyLocation(true);
                if (!isMyLocationEnabled) {
                    Toast.makeText(this, "Please enable a My Location source in system settings",
                            Toast.LENGTH_LONG).show();

                    Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(goToSettings);
                }
            }
        }
    }

    private void stopMyLocation() {
        if (nMapMyLocationOverlay != null) {
            nMapLocationManager.disableMyLocation();

            if (nMapView.isAutoRotateEnabled()) {
                nMapMyLocationOverlay.setCompassHeadingVisible(false);

                nMapCompassManager.disableCompass();

                nMapView.setAutoRotateEnabled(false, false);
            }
        }
    }

    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {

            if (nMapController != null) {
                nMapController.animateTo(myLocation);
            }

            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {

            // stop location updating
            //			Runnable runnable = new Runnable() {
            //				public void run() {
            //					stopMyLocation();
            //				}
            //			};
            //			runnable.run();

            Toast.makeText(getApplicationContext(), "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {

            Toast.makeText(getApplicationContext(), "Your current location is unavailable area.", Toast.LENGTH_LONG).show();

            stopMyLocation();
        }

    };

    private void updateZoomFAB() {
        if (nMapController.canZoomIn())
            zoomInFAB.show();
        else
            zoomInFAB.hide();
        if (nMapController.canZoomOut())
            zoomOutFAB.show();
        else
            zoomOutFAB.hide();
    }

    private void addMarker() {
        int markerId = NMapPOIFlagType.PIN;
        // set POI data
        NMapPOIdata poiData = new NMapPOIdata(2, nMapViewerResourceProvider);
        new JSONTaskAll(this).setTaskListener(houseList -> {
            Log.d("data", houseList.size() + "");
            poiData.beginPOIdata(houseList.size());
            for (HouseItem item : houseList){
                poiData.addPOIitem(item.get위도(), item.get경도(), item.get건물명(), markerId, 0);
            }
            poiData.endPOIdata();
            // create POI data overlay
            NMapPOIdataOverlay poiDataOverlay = nOverlayManager.createPOIdataOverlay(poiData, null);
        }).execute("http://10.80.161.54:80/api/Alltrade");
//        poiData.beginPOIdata(2);
//        poiData.addPOIitem(127.0630205, 37.5091300, "Pizza 777-111", markerId, 0);
//        poiData.addPOIitem(127.061, 37.51, "Pizza 123-456", markerId, 0);

        //poiDataOverlay.selectPOIitem(0, true);
    }


    /**
     * 지도가 초기화된 후 호출된다.
     * 정상적으로 초기화되면 errorInfo 객체는 null이 전달되며,
     * 초기화 실패 시 errorInfo 객체에 에러 원인이 전달된다
     */
    @Override
    public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
        if (errorInfo == null) { // success
            nMapController.setMapCenter(
                    new NGeoPoint(127.985835, 35.994213), 3);
        } else { // fail
            Log.e("NMAP", "onMapInitHandler: " + errorInfo.toString());
        }
    }

    /**
     * 지도 레벨 변경 시 호출되며 변경된 지도 레벨이 파라미터로 전달된다.
     */
    @Override
    public void onZoomLevelChange(NMapView nMapView, int level) {
        updateZoomFAB();
    }

    /**
     * 지도 중심 변경 시 호출되며 변경된 중심 좌표가 파라미터로 전달된다.
     */
    @Override
    public void onMapCenterChange(NMapView mapview, NGeoPoint center) {
    }

    /**
     * 지도 애니메이션 상태 변경 시 호출된다.
     * animType : ANIMATION_TYPE_PAN or ANIMATION_TYPE_ZOOM
     * animState : ANIMATION_STATE_STARTED or ANIMATION_STATE_FINISHED
     */
    @Override
    public void onAnimationStateChange(
            NMapView nMapView, int animType, int animState) {
    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {
    }

    @Override
    public void onStart() {
        super.onStart();
        nMapContext.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        nMapContext.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        nMapContext.onPause();
    }

    @Override
    public void onStop() {
        nMapContext.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        nMapContext.onDestroy();
        super.onDestroy();
    }
}

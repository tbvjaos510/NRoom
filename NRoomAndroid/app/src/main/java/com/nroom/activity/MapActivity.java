package com.nroom.activity;

import android.os.Bundle;
import android.util.Log;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nroom.R;

public class MapActivity extends NMapActivity
        implements OnMapStateChangeListener {

    //FIXME Add Renamed Package
    // CLIENT ID
    public static final String CLIENT_ID = "qxSxM3dHkv_nQodnLE34";  //<---맨위에서 발급받은 본인 Client ID 넣으세요.

    // 네이버 맵 객체
    NMapView nMapView;

    // 맵 컨트롤러
    NMapController nMapController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // 네이버 지도 컴포넌트
        nMapView = findViewById(R.id.n_map);

        // 지도 객체로부터 컨트롤러 추출
        nMapController = nMapView.getMapController();

        // 네이버 지도 객체에 CLIENT ID 지정
        nMapView.setClientId(CLIENT_ID);

        // 확대/축소를 위한 줌 컨트롤러 표시 옵션 활성화
        nMapView.setBuiltInZoomControls(true, null);

        // 지도에 대한 상태 변경 이벤트 연결
        nMapView.setOnMapStateChangeListener(this);
    }

    /**
     * 지도가 초기화된 후 호출된다.
     * 정상적으로 초기화되면 errorInfo 객체는 null이 전달되며,
     * 초기화 실패 시 errorInfo객체에 에러 원인이 전달된다
     */
    @Override
    public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
        if (errorInfo == null) { // success
            nMapController.setMapCenter(
                    new NGeoPoint(126.978371, 37.5666091), 11);
        } else { // fail
            Log.e("NMAP", "onMapInitHandler: " + errorInfo.toString());
        }
    }

    /**
     * 지도 레벨 변경 시 호출되며 변경된 지도 레벨이 파라미터로 전달된다.
     */
    @Override
    public void onZoomLevelChange(NMapView mapview, int level) {
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
}

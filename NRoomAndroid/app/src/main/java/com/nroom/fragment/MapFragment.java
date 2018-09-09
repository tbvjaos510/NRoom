package com.nroom.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nroom.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MapFragment extends Fragment
        implements NMapView.OnMapStateChangeListener {
    private static final String CLIENT_ID = "qxSxM3dHkv_nQodnLE34";// 애플리케이션 클라이언트 아이디 값
    private NMapView nMapView;
    private NMapContext nMapContext;
    private NMapController nMapController;
    private FloatingActionButton zoomInFAB;
    private FloatingActionButton zoomOutFAB;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        nMapView = view.findViewById(R.id.n_map);

        zoomInFAB = view.findViewById(R.id.zoom_in_fab);
        zoomOutFAB = view.findViewById(R.id.zoom_out_fab);
        nMapContext.setupMapView(nMapView);

        nMapController = nMapView.getMapController();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null) {
            nMapContext = new NMapContext(getActivity());
            nMapContext.onCreate();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    }

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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        nMapContext.onDestroy();
        super.onDestroy();
    }
}

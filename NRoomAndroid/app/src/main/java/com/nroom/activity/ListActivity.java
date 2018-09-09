package com.nroom.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nroom.R;
import com.nroom.listview.ListViewAdapter;
import com.nroom.network.JSONTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ListActivity extends AppCompatActivity {
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            String provider = location.getProvider();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private String selectdeLocal = "서울특별시";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnLocal = findViewById(R.id.btnLocal);
        btnLocal.setOnClickListener(view -> {
            InitSpinner(view);
        });

        ToggleButton btnGps = findViewById(R.id.btnGps);
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnGps.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{
                    if(btnGps.isChecked()){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1,mLocationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                    }else{
                        locationManager.removeUpdates(mLocationListener);
                    }
                }catch (SecurityException ex){

                }
            }
        });

        mListView = findViewById(R.id.sale_listview);

        dataSetting();
    }

    private void dataSetting() {
        ListViewAdapter listViewAdapter = new ListViewAdapter();

        for(int i=0; i<10; i++){
            listViewAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "price_" + i, "location_" + i, "nociet_" + i);
        }

        mListView.setAdapter(listViewAdapter);
    }

    private void InitSpinner(View view) {
        String before = selectdeLocal;
        Spinner localSpinner = findViewById(R.id.localSpinner);
        localSpinner.setVisibility(view.VISIBLE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.location,android.R.layout.simple_spinner_item);

        localSpinner.setAdapter(adapter);

        localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectdeLocal = (String) parent.getItemAtPosition(position);
                if(!selectdeLocal.equals(before)){
                    //TODO: 서버랑 연동
                    localSpinner.setVisibility(view.INVISIBLE);
                    selectdeLocal = "서울특별시";
                    return ;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectdeLocal = "";
            }
        });
        return ;
    }
}

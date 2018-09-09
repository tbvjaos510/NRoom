package com.nroom.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nroom.R;
import com.nroom.data.HouseItem;
import com.nroom.listview.ListViewAdapter;
import com.nroom.network.JSONTask;
import com.nroom.recyclerview.HouseAdapter;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends BaseActivity {
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            String provider = location.getProvider();
            Log.v("12312", longitude + "");
            Log.v("12312", latitude + " ");
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

    private String selectedLocal = "서울특별시";
    private RecyclerView recyclerView;
    private HouseAdapter houseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setSupportActionBar(findViewById(R.id.toolbar));

        Button btnLocal = findViewById(R.id.btnLocal);
        btnLocal.setOnClickListener(view -> {
           // InitSpinner(view);
        });

        ToggleButton btnGps = findViewById(R.id.btnGps);
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (btnGps.isChecked()) {
                        Log.v("12312","클릭");
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, mLocationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                    } else {
                        locationManager.removeUpdates(mLocationListener);
                    }
                } catch (SecurityException ignore) {
                }
            }
        });

        recyclerView = findViewById(R.id.sale_recycler_view);
        houseAdapter = new HouseAdapter(dataSetting());
        recyclerView.setAdapter(houseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private ArrayList<HouseItem> dataSetting() {
        new JSONTask().execute("http://10.80.161.54:80/api/realtrade?시도명=서울특별시");
        Log.v("12312","4");
        ArrayList<HouseItem> houseItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            houseItems.add(new HouseItem(getDrawable(R.drawable.icon), "price_" + i, "location_" + i, "notice_" + i));
        }
        houseItems.add(new HouseItem(getDrawable(R.drawable.icon), "price_" + 10, "location_" + 10, "notice_" + new HouseItem().getTest()));
        return houseItems;
    }

    private void InitSpinner(View view) {
        String before = selectedLocal;
        Spinner localSpinner = findViewById(R.id.localSpinner);
        localSpinner.setVisibility(View.VISIBLE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);

        localSpinner.setAdapter(adapter);

        localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocal = (String) parent.getItemAtPosition(position);
                if (!selectedLocal.equals(before)) {
                    //TODO: 서버랑 연동
                    localSpinner.setVisibility(View.INVISIBLE);
                    selectedLocal = "서울특별시";
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLocal = "";
            }
        });
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnLocal = findViewById(R.id.btnLocal);
        btnLocal.setOnClickListener(view -> {
            InitSpinner(view);
        });

        ToggleButton btnGps = findViewById(R.id.btnGps);
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        btnGps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if (btnGps.isChecked()) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, mLocationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                    } else {
                        locationManager.removeUpdates(mLocationListener);
                    }
                } catch (SecurityException ignore) {
                }
            }
        });

        mListView = findViewById(R.id.sale_listview);

        dataSetting();
    }

    private void dataSetting() {
        ListViewAdapter listViewAdapter = new ListViewAdapter();

        for (int i = 0; i < 10; i++) {
            listViewAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "price_" + i, "location_" + i, "nociet_" + i);
        }

        mListView.setAdapter(listViewAdapter);
    }

    private void InitSpinner(View view) {
        String before = selectedLocal;
        Spinner localSpinner = findViewById(R.id.localSpinner);
        localSpinner.setVisibility(View.VISIBLE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);

        localSpinner.setAdapter(adapter);

        localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocal = (String) parent.getItemAtPosition(position);
                if (!selectedLocal.equals(before)) {
                    //TODO: 서버랑 연동
                    localSpinner.setVisibility(View.INVISIBLE);
                    selectedLocal = "서울특별시";
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLocal = "";
            }
        });
        return;
    }*/
}

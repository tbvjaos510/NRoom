package com.nroom.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.nroom.R;
import com.nroom.data.StaticResources;
import com.nroom.network.JSONTask;
import com.nroom.recycler.HouseAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends BaseActivity {
    private String selectedLocal = "서울특별시";
    private String selectedCondition = "전체";
    private RecyclerView recyclerView;
    private HouseAdapter houseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

       // new JSONTaskTraffic(null).execute("http://10.80.161.54/api/busPos?lat=37.505217&lng=127.025389");

        setSupportActionBar(findViewById(R.id.toolbar));

    //    Button btnLocal = findViewById(R.id.btnLocal);
        InitSpinner();

        recyclerView = findViewById(R.id.sale_recycler_view);
        houseAdapter = new HouseAdapter(this);
        recyclerView.setAdapter(houseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getHouseData(String local, String condition) {
        if (local != null) {
            if (selectedCondition.equals("전체")) {
                new JSONTask(this).setTaskListener(houseList ->  {
                    houseAdapter.setHouseItems(houseList);
                }).execute(StaticResources.HOST + "/api/juntrade?시도명=" + local);
                new JSONTask(this).setTaskListener(houseList ->  {
                    houseAdapter.addHouseItems(houseList);
                }).execute(StaticResources.HOST + "/api/realtrade?시도명=" + local);
            } else {
                new JSONTask(this).setTaskListener(houseList ->  {
                    houseAdapter.setHouseItems(houseList);
                }).execute(StaticResources.HOST + "/api/juntrade?시도명=" + local + "&검색조건=" + selectedCondition);
                new JSONTask(this).setTaskListener(houseList ->  {
                    houseAdapter.addHouseItems(houseList);
                }).execute(StaticResources.HOST + "/api/realtrade?시도명=" + local + "&검색조건=" + selectedCondition);
            }
        }
    }

    private void InitSpinner() {
        String before = selectedLocal;
        Spinner localSpinner = findViewById(R.id.localSpinner);
        Spinner conditionSpinner = findViewById(R.id.conditionSpinner);

        localSpinner.setVisibility(View.VISIBLE);
        conditionSpinner.setVisibility(View.VISIBLE);

        ArrayAdapter localAdapter = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        ArrayAdapter conditionAdapter = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);

        localSpinner.setAdapter(localAdapter);
        conditionSpinner.setAdapter(conditionAdapter);

        localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocal = (String) parent.getItemAtPosition(position);
                getHouseData(selectedLocal, null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLocal = null;
            }
        });

        conditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCondition = (String) parent.getItemAtPosition(position);
                getHouseData(selectedLocal, selectedCondition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCondition = null;
            }
        });
    }

    public void onCheckboxClicked(View view) {
        CheckBox Month = findViewById(R.id.Month);
        CheckBox Year = findViewById(R.id.Year);
        CheckBox Trade = findViewById(R.id.Trade);

        houseAdapter.setShowMonth(Month.isChecked());
        houseAdapter.setShowYear(Year.isChecked());
        houseAdapter.setShowTrade(Trade.isChecked());

        houseAdapter.updateList();
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
    }




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
    */
}

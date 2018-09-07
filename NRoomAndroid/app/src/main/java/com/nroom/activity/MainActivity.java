package com.nroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.nroom.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}

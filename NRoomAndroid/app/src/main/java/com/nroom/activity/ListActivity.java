package com.nroom.activity;

import android.os.Bundle;
import android.widget.Button;

import com.nroom.R;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnLocal = findViewById(R.id.btnLocal);
        btnLocal.setOnClickListener(view -> {
            /*Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);*/
        });
    }
}

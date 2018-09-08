package com.nroom.activity;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    protected <T extends View> T bind(int id) {
        return findViewById(id);
    }
}

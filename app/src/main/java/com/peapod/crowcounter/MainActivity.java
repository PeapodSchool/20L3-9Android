package com.peapod.crowcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int crows = 0;
    public static final String KEY = "crows";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        settings = getSharedPreferences(KEY, Context.MODE_PRIVATE);
        if (savedInstanceState != null) {
            crows = savedInstanceState.getInt(KEY);
            textView.setText("Насчитано " + crows + " ворон");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (settings.contains(KEY)) {
            crows = settings.getInt(KEY, 0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY, crows);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, crows);
    }

    public void count(View view) {
        crows++;
        textView.setText("Насчитано " + crows + " ворон");
    }
}
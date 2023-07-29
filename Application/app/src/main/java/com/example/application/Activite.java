package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite);

        getSupportActionBar().hide();
    }
}
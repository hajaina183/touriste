package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Dans votre Activity, recherchez le TextView dans le layout par son ID (supposons que l'ID du TextView est "textViewData")
        TextView textViewData = findViewById(R.id.test);

        // Utilisez la méthode setText pour afficher la donnée dans le TextView
        textViewData.setText("hello baby");
    }
}
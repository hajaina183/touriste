package com.example.application;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class TestVariable extends AppCompatActivity {

    private String myVariable = "Contenu de ma variable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_variable);

        // Dans votre Activity, recherchez le TextView dans le layout par son ID (supposons que l'ID du TextView est "textViewData")
        //TextView textViewData = findViewById(R.id.textView);

        // Utilisez la méthode setText pour afficher la donnée dans le TextView
        //textViewData.setText("hello baby");

    }
}
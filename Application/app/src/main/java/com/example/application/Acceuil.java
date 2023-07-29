package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Acceuil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        getSupportActionBar().hide();

        CardView cardView1 = findViewById(R.id.endroitPopulaire);
        CardView cardView2 = findViewById(R.id.activite);
        CardView cardView3 = findViewById(R.id.culture);
        CardView cardView4 = findViewById(R.id.conseil);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Acceuil.this, AppleActivity.class);
                startActivity(intent);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Acceuil.this, Conseil.class);
                startActivity(intent);
            }
        });
    }
}
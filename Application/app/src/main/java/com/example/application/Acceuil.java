package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.application.model.EndroitPopulaire;

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
                
                Intent intent = new Intent(Acceuil.this, EndroitPopulaireActivity.class);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Acceuil.this, Activite.class);
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Acceuil.this, Culture.class);
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

        // Ajouter le FooterFragment dans le conteneur
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.footerFragment, new FooterFragment())
                    .commit();
        }
    }
}
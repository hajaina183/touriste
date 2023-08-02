package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite);

        getSupportActionBar().hide();

        CardView cardView1 = findViewById(R.id.randonnee);
        CardView cardView2 = findViewById(R.id.plonge);
        CardView cardView3 = findViewById(R.id.faune);
        CardView cardView4 = findViewById(R.id.baleine);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Activite.this, Randonne.class);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Activite.this, Plonge.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Activite.this, Faune.class);
                startActivity(intent);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la nouvelle activité lorsque le CardView est cliqué
                Intent intent = new Intent(Activite.this, Baleine.class);
                startActivity(intent);
            }
        });
        // Ajouter le FooterFragment dans le conteneur
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.footerFragment, new FooterFragment())
                    .commit();
        }
        /*cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacez "VotreNouveauFragment" par le nom de la classe de votre nouveau fragment à ouvrir
                Fragment nouveauFragment = new Randone();

                // Utilisez FragmentManager pour ouvrir le nouveau fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainContainer, nouveauFragment);
                fragmentTransaction.addToBackStack(null); // Si vous souhaitez ajouter cette transition à la pile de fragments
                fragmentTransaction.commit();
            }
        });*/
    }
}
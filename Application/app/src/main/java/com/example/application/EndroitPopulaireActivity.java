package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.model.EndroitPopulaire;
import com.example.application.model.Parc;
import com.example.application.model.Plage;
import com.example.application.model.Site;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndroitPopulaireActivity extends AppCompatActivity {

    private static final String TAG = "EndroitPopulaireActivity";

    private String selectedOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endroit_populaire);

        getSupportActionBar().hide();

        CardView cardView1 = findViewById(R.id.parc1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EndroitPopulaireActivity.this, DetailParc1.class);
                startActivity(intent);
            }
        });

        // Ajouter le FooterFragment dans le conteneur
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.footerFragment, new FooterFragment())
                    .commit();
        }

        String[] options = {"Parc","Plage", "Site"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, options);

        Spinner spinnerSearch = findViewById(R.id.spinnerSearch);
        spinnerSearch.setAdapter(adapter);
        spinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption = options[position]; // Met à jour la variable selectedOption lorsque l'utilisateur sélectionne une option
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Action à réaliser lorsque rien n'est sélectionné
            }
        });
        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à réaliser lorsque l'utilisateur clique sur le bouton "Rechercher"
                if (!selectedOption.isEmpty()) {
                    Toast.makeText(EndroitPopulaireActivity.this, "Option sélectionnée : " + selectedOption, Toast.LENGTH_SHORT).show();
                    if (selectedOption.equals("Parc")) {
                        findViewById(R.id.textparc).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutparc).setVisibility(View.VISIBLE);

                        findViewById(R.id.textplage).setVisibility(View.GONE);
                        findViewById(R.id.layoutplage).setVisibility(View.GONE);
                        findViewById(R.id.textsite).setVisibility(View.GONE);
                        findViewById(R.id.layoutsite).setVisibility(View.GONE);

                    }
                    if (selectedOption.equals("Plage")) {
                        findViewById(R.id.textplage).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutplage).setVisibility(View.VISIBLE);

                        findViewById(R.id.textparc).setVisibility(View.GONE);
                        findViewById(R.id.layoutparc).setVisibility(View.GONE);
                        findViewById(R.id.textsite).setVisibility(View.GONE);
                        findViewById(R.id.layoutsite).setVisibility(View.GONE);

                    }if (selectedOption.equals("Site")) {
                        findViewById(R.id.textsite).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutsite).setVisibility(View.VISIBLE);

                        findViewById(R.id.textplage).setVisibility(View.GONE);
                        findViewById(R.id.layoutplage).setVisibility(View.GONE);
                        findViewById(R.id.textparc).setVisibility(View.GONE);
                        findViewById(R.id.layoutparc).setVisibility(View.GONE);


                    }
                } else {
                    Toast.makeText(EndroitPopulaireActivity.this, "Veuillez sélectionner une option dans le Spinner.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView description1 = findViewById(R.id.description_1);
        ImageView image1 = findViewById(R.id.image_1);
        TextView description2 = findViewById(R.id.description_2);
        ImageView image2 = findViewById(R.id.image_2);
        TextView description3 = findViewById(R.id.description_3);
        ImageView image3 = findViewById(R.id.image_3);


        TextView plagedes = findViewById(R.id.plage_1);
        ImageView plageim = findViewById(R.id.imageplage_1) ;
        TextView plagedes1 = findViewById(R.id.plage_2);
        ImageView plageim1 = findViewById(R.id.imageplage_2) ;
        TextView plagedes2 = findViewById(R.id.plage_3);
        ImageView plageim2 = findViewById(R.id.imageplage_3) ;


        TextView descSite1 = findViewById(R.id.descriptionsite_1);
        ImageView siteimage1 = findViewById(R.id.siteimage1) ;
        TextView descSite2 = findViewById(R.id.descriptionsite_2);
        ImageView siteimage2 = findViewById(R.id.siteimage2) ;
        TextView descSite3 = findViewById(R.id.descriptionsite_3);
        ImageView siteimage3 = findViewById(R.id.siteimage3) ;





        Parc e = new Parc();
        e.getData(new Callback<List<Parc>>() {

            @Override
            public void onResponse(Call<List<Parc>> call, Response<List<Parc>> response) {
                if (response.isSuccessful()) {
                    List<Parc> parcs = response.body();
                    Log.e(TAG, "misy v izy aloooooooooo ???????????? " + parcs.size());
                    description1.setText(parcs.get(0).getNom());
                    int resId = getResources().getIdentifier(parcs.get(0).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    image1.setImageDrawable(drawable);

                    description2.setText(parcs.get(1).getNom());
                    int resId2 = getResources().getIdentifier(parcs.get(1).getPhoto(), "drawable", getPackageName());
                    Drawable drawable2 = getResources().getDrawable(resId2);
                    image2.setImageDrawable(drawable2);

                    description3.setText(parcs.get(2).getNom());
                    int resId3 = getResources().getIdentifier(parcs.get(2).getPhoto(), "drawable", getPackageName());
                    Drawable drawable3 = getResources().getDrawable(resId3);
                    image3.setImageDrawable(drawable3);
                } else {
                    Log.e(TAG, "dans else");
                }
            }

            @Override
            public void onFailure(Call<List<Parc>> call, Throwable t) {


            }
        });


        Plage p = new Plage();
        p.getData(new Callback<List<Plage>>() {
            @Override
            public void onResponse(Call<List<Plage>> call, Response<List<Plage>> response) {
                if (response.isSuccessful()) {
                    List<Plage> plages = response.body();
                    plagedes.setText(plages.get(0).getNom());
                    int resId = getResources().getIdentifier(plages.get(0).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    plageim.setImageDrawable(drawable);

                    plagedes1.setText(plages.get(1).getNom());
                    int resId1 = getResources().getIdentifier(plages.get(1).getPhoto(), "drawable", getPackageName());
                    Drawable drawable1 = getResources().getDrawable(resId1);
                    plageim1.setImageDrawable(drawable1);

                    plagedes2.setText(plages.get(2).getNom());
                    int resId2 = getResources().getIdentifier(plages.get(2).getPhoto(), "drawable", getPackageName());
                    Drawable drawable2= getResources().getDrawable(resId2);
                    plageim2.setImageDrawable(drawable2);
                }else {
                    Log.e(TAG, "dans else");
                }
            }

            @Override
            public void onFailure(Call<List<Plage>> call, Throwable t) {

            }
        });

        Site s = new Site();
        s.getData(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.isSuccessful()) {
                    List<Site> sites = response.body();
                    descSite1.setText(sites.get(0).getNom());
                    int resId = getResources().getIdentifier(sites.get(0).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    siteimage1.setImageDrawable(drawable);

                    descSite2.setText(sites.get(1).getNom());
                    int resId2 = getResources().getIdentifier(sites.get(1).getPhoto(), "drawable", getPackageName());
                    Drawable drawable2 = getResources().getDrawable(resId2);
                    siteimage2.setImageDrawable(drawable2);

                    descSite3.setText(sites.get(2).getNom());
                    int resId3 = getResources().getIdentifier(sites.get(2).getPhoto(), "drawable", getPackageName());
                    Drawable drawable3 = getResources().getDrawable(resId3);
                    siteimage3.setImageDrawable(drawable3);

                }else {
                    Log.e(TAG, "dans else");
                }
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {

            }
        });


    }

}


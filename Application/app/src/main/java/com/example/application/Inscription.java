package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.application.model.Profil;
import com.google.android.material.textfield.TextInputEditText;

public class Inscription extends AppCompatActivity {
    private static final String TAG = "Inscription";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        TextInputEditText userText , passwordText , nomText , prenomText , adresseText;

        Button btn ;

        userText = (TextInputEditText) findViewById(R.id.user);
        passwordText = (TextInputEditText) findViewById(R.id.password);
        nomText = (TextInputEditText)findViewById(R.id.nom);
        prenomText = findViewById(R.id.prenom);
        adresseText = (TextInputEditText)findViewById(R.id.adresse);

        btn = (Button) findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userText.getText().toString();
                String password = passwordText.getText().toString();
                String nom = nomText.getText().toString();
                String prenom = prenomText.getText().toString();
                String adresse = adresseText.getText().toString();

                Log.e(TAG, "onResponse: name : "+username);
                Log.e(TAG, "onResponse: prenom : "+prenom);
                Log.e(TAG, "onResponse: adresse : "+adresse);

                Profil profil = new Profil();
                profil.inscription(nom, prenom, adresse, username, adresse, new Profil.InscriptionCallBack() {
                    @Override
                    public void onLoginResult(boolean isSuccess) {
                        if (isSuccess) {
                            Log.i(TAG, "inscription ok: success");
                            startActivity(new Intent(Inscription.this,Acceuil.class));
                        } else {
                            Log.e(TAG, "inscription: error");
                        }
                    }
                });

            }
        });


    }

    public void seConnecter(View view){
        startActivity(new Intent(Inscription.this,LoginActivity.class));
    }
}
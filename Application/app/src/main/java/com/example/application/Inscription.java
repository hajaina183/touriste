package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.application.model.Profil;
import com.google.android.material.textfield.TextInputEditText;

public class Inscription extends AppCompatActivity {
    private static final String TAG = "Inscription";
    private static final String CHANNEL_ID = "my_channel_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        getSupportActionBar().hide();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            CharSequence name = "My Channel";
            String description = "My Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


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
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Inscription.this, CHANNEL_ID)
                                    .setSmallIcon(R.drawable.ic_notification)
                                    .setContentTitle("Touris Mada") // Titre de la notification
                                    .setContentText("Merci pour votre inscription") // Contenu de la notification
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);// Priorité de la notification

                            NotificationManager notificationManager = getSystemService(NotificationManager.class);
                            notificationManager.notify(1, builder.build()); // Affichez la notification avec l'ID 1
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
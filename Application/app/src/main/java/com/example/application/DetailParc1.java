package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.model.Parc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailParc1 extends AppCompatActivity {
    private static final String TAG = "DetailParc1";
    private  String nomParc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parc1);

        ImageView imageViewSend = findViewById(R.id.imageViewSend);

        TextView titre = findViewById(R.id.titre);
        TextView descrption = findViewById(R.id.description);
        ImageView image1 = findViewById(R.id.imageViewId);

        SharedPreferences sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user", "");
        Parc e = new Parc();
        e.getData(new Callback<List<Parc>>() {

            @Override
            public void onResponse(Call<List<Parc>> call, Response<List<Parc>> response) {
                if (response.isSuccessful()) {

                    List<Parc> parcs = response.body();
                    Log.e(TAG, "misy v izy aloooooooooo ???????????? " + parcs.size());
                    titre.setText(parcs.get(0).getNom()+" se trouve à " + parcs.get(0).getLieu());
                    //titre.setText(userId);
                    nomParc = parcs.get(0).getNom() ;
                    descrption.setText(parcs.get(0).getDescription());
                    int resId = getResources().getIdentifier(parcs.get(0).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    image1.setImageDrawable(drawable);


                } else {
                    Log.e(TAG, "dans else");
                }
            }

            @Override
            public void onFailure(Call<List<Parc>> call, Throwable t) {


            }
        });

        imageViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                String formattedDate = sdf.format(currentDate);
                Date date = convertStringToDate(formattedDate);
                // Récupérer le contenu du commentaire depuis l'EditText
                EditText editTextComment = findViewById(R.id.editTextComment);
                String commentaire = editTextComment.getText().toString();

                // Vérifier si le commentaire n'est pas vide
                if (!commentaire.isEmpty()) {
                    Log.e(TAG,"****************");
                    Log.e(TAG, "nomParc: " + nomParc );
                    Log.e(TAG, "date: " + date );
                    Log.e(TAG, "commentaire: " + commentaire );
                    Log.e(TAG, "userId: " + userId );
                    e.insertCommentaireParc(nomParc, date, commentaire, userId, new Parc.InsertCommentaire() {
                        @Override
                        public void onLoginResult(boolean isSuccess) {
                            if (isSuccess) {
                                editTextComment.setText("");
                            } else {
                                Log.e(TAG, "inscription: error");
                            }
                        }
                    });
                } else {
                    // Afficher un toast ou une boîte de dialogue pour informer l'utilisateur que le commentaire est vide
                    Toast.makeText(DetailParc1.this, "Veuillez saisir un commentaire.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
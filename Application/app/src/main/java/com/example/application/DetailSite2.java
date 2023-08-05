package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.model.Parc;
import com.example.application.model.Site;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSite2 extends AppCompatActivity {

    private  String nomParc = "";
    private static final String TAG = "DetailSite1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_site2);

        getSupportActionBar().hide();

        ImageView imageViewSend = findViewById(R.id.imageViewSend);

        TextView titre = findViewById(R.id.titre);
        TextView descrption = findViewById(R.id.description);
        ImageView image1 = findViewById(R.id.imageViewId);

        SharedPreferences sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user", "");
        Site s = new Site();
        s.getData(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.isSuccessful()) {

                    List<Site> sites = response.body();
                    titre.setText(sites.get(1).getNom()+" se trouve à " + sites.get(1).getLieu());
                    //titre.setText(userId);
                    nomParc = sites.get(1).getNom() ;
                    descrption.setText(sites.get(1).getDescription());
                    int resId = getResources().getIdentifier(sites.get(1).getPhoto(), "drawable", getPackageName());
                    Drawable drawable = getResources().getDrawable(resId);
                    image1.setImageDrawable(drawable);


                } else {
                    Log.e(TAG, "dans else");
                }

            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {

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
                    s.insertCommentaireSite(nomParc, date, commentaire, userId, new Site.InsertCommentaire() {
                        @Override
                        public void onLoginResult(boolean isSuccess) {
                            if (isSuccess) {
                                editTextComment.setText(" ");
                            } else {
                                Log.e(TAG, "inscription: error");
                            }
                        }
                    });
                } else {
                    // Afficher un toast ou une boîte de dialogue pour informer l'utilisateur que le commentaire est vide
                    Toast.makeText(DetailSite2.this, "Veuillez saisir un commentaire.", Toast.LENGTH_SHORT).show();
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
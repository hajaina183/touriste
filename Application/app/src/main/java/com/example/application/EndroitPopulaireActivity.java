package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application.model.EndroitPopulaire;
import com.example.application.model.Parc;
import com.example.application.model.Plage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndroitPopulaireActivity extends AppCompatActivity {

    private static final String TAG = "EndroitPopulaireActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endroit_populaire);


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


    }




}


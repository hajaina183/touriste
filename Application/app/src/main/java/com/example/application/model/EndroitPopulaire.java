package com.example.application.model;

import android.util.Log;

import com.example.application.RetrofitClient;
import com.example.application.controler.EndroitPopulaireControler;
import com.example.application.controler.Methods;
import com.example.application.controler.ParcController;
import com.example.application.controler.ProfilControler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndroitPopulaire {

    Parc[] parc ;

    public Parc[] getParc() {
        return parc;
    }

    public void setParc(Parc[] parc) {
        this.parc = parc;
    }

    private static final String TAG = "EndroitPopulaire";


    public void getData(Callback<List<Parc>> callback) {
        ParcController methods = RetrofitClient.getRetrofitInstance().create(ParcController.class);
        Call<List<Parc>> call = methods.getAllParc();
        Log.e(TAG, "misy v izy ???????????? " + call);
        // Effectuer l'appel asynchrone
        call.enqueue(callback);
    }
}

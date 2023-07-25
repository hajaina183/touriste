package com.example.application.model;

import android.util.Log;

import com.example.application.RetrofitClient;
import com.example.application.controler.ProfilControler;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil {
    String id;
    String nom;
    String prenom;
    String adresse;
    String user;
    String mdp;
    private static final String TAG = "MainActivity";

    public Profil() {
    }

    public Profil(String id, String nom, String prenom, String adresse, String user, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.user = user;
        this.mdp = mdp;
    }

    public Profil(String nom, String prenom, String adresse, String user, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.user = user;
        this.mdp = mdp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public interface LoginCallback {
        void onLoginResult(boolean isSuccess);
    }


    public void traitementLogin(String user, String mdp, final LoginCallback callback) {
        ProfilControler profilControler = RetrofitClient.getRetrofitInstance().create(ProfilControler.class);
        Call<Profil> call = profilControler.traitementLogin(user, mdp);
        call.enqueue(new Callback<Profil>() {
            @Override
            public void onResponse(Call<Profil> call, Response<Profil> response) {
                if (response.isSuccessful()) {
                    if(response.body().getNom() != null && !response.body().getNom().isEmpty()) {
                        callback.onLoginResult(true);
                    } else {
                        callback.onLoginResult(false);
                    }
                } else {
                    Log.e(TAG, "traitementLogin: Code d'erreur : " + response.code());
                    callback.onLoginResult(false);
                }
            }

            @Override
            public void onFailure(Call<Profil> call, Throwable t) {
                t.printStackTrace();
                callback.onLoginResult(false);
            }
        });
    }


    public boolean traitementLogin(String user, String mdp) {
        ProfilControler profilControler = RetrofitClient.getRetrofitInstance().create(ProfilControler.class);
        Call<Profil> call = profilControler.traitementLogin(user, mdp);
        try {
            Response<Profil> response = call.execute();
            if (response.isSuccessful()) {
                if(response.body().getNom() != "") {
                    return true;
                } else {
                    return false;
                }
            } else {
                Log.e(TAG, "traitementLogin: Code d'erreur : " + response.code());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

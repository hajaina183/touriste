package com.example.application.model;

import android.util.Log;

import com.example.application.RetrofitClient;
import com.example.application.controler.ParcController;
import com.example.application.controler.ProfilControler;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Parc {


    String id ;
    String nom;

    public Parc() {
    }

    String photo ;
    String lieu ;
    String description ;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Parc(String id, String nom, String photo, String lieu, String description) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.lieu = lieu;
        this.description = description;
    }

    public void getData(Callback<List<Parc>> callback) {
        ParcController methods = RetrofitClient.getRetrofitInstance().create(ParcController.class);
        Call<List<Parc>> call = methods.getAllParc();
        // Effectuer l'appel asynchrone
        call.enqueue(callback);
    }

    public interface InsertCommentaire {
        void onLoginResult(boolean isSuccess);
    }

    public  void insertCommentaireParc (String nom , Date date, String text, String user,final Parc.InsertCommentaire callBack){
        ParcController parcController = RetrofitClient.getRetrofitInstance().create(ParcController.class);
        Call<Profil> call = parcController.insertCommentaireParc(nom, date, text, user);
        call.enqueue(new Callback<Profil>() {
            @Override
            public void onResponse(Call<Profil> call, Response<Profil> response) {
                if (response.isSuccessful()) {
                    if(response.body().getNom() != null && !response.body().getNom().isEmpty()) {

                        callBack.onLoginResult(true);
                    } else {
                        callBack.onLoginResult(false);
                    }
                } else {

                    callBack.onLoginResult(false);
                }
            }

            @Override
            public void onFailure(Call<Profil> call, Throwable t) {
                t.printStackTrace();
                callBack.onLoginResult(false);
            }
        });
    }

}

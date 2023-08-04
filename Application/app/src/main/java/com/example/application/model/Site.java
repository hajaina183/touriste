package com.example.application.model;

import com.example.application.RetrofitClient;
import com.example.application.controler.ParcController;
import com.example.application.controler.PlageController;
import com.example.application.controler.SiteController;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Site {

    String id ;
    String nom;
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

    public Site(String id, String nom, String photo, String lieu, String description) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.lieu = lieu;
        this.description = description;
    }

    public Site() {
    }

    public void getData(Callback<List<Site>> callback) {
        SiteController methods = RetrofitClient.getRetrofitInstance().create(SiteController.class);
        Call<List<Site>> call = methods.getAllSite();
        // Effectuer l'appel asynchrone
        call.enqueue(callback);
    }

    public interface InsertCommentaire {
        void onLoginResult(boolean isSuccess);
    }


    public  void insertCommentaireSite (String nom , Date date, String text, String user, final Site.InsertCommentaire callBack){
        SiteController parcController = RetrofitClient.getRetrofitInstance().create(SiteController.class);
        Call<Profil> call = parcController.insertCommentaireSite(nom, date, text, user);
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

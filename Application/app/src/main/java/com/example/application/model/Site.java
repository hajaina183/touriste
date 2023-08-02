package com.example.application.model;

import com.example.application.RetrofitClient;
import com.example.application.controler.PlageController;
import com.example.application.controler.SiteController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

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
}

package com.example.application.model;

import android.util.Log;

import com.example.application.RetrofitClient;
import com.example.application.controler.ParcController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

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

}

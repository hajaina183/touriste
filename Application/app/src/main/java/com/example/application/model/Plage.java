package com.example.application.model;

import com.example.application.RetrofitClient;
import com.example.application.controler.PlageController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Plage {

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

    public Plage() {
    }

    public Plage(String id, String nom, String photo, String lieu, String description) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.lieu = lieu;
        this.description = description;
    }

    public void getData(Callback<List<Plage>> callback) {
        PlageController methods = RetrofitClient.getRetrofitInstance().create(PlageController.class);
        Call<List<Plage>> call = methods.getAllPlages();
        // Effectuer l'appel asynchrone
        call.enqueue(callback);
    }
}

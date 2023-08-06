package com.example.application.model;

import com.example.application.RetrofitClient;
import com.example.application.controler.ParcController;
import com.example.application.controler.PlageController;
import com.example.application.controler.SiteController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Commentaire {
    private String date;
    private String text;
    private String user;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void getCommentairesParc(String nomParc, Callback<List<Commentaire>> callback) {
        ParcController parcController = RetrofitClient.getRetrofitInstance().create(ParcController.class);
        Call<List<Commentaire>> call = parcController.getAllCommentaire(nomParc);
        call.enqueue(callback);
    }

    public void getCommentairesPlage(String nomPlage, Callback<List<Commentaire>> callback) {
        PlageController plageController = RetrofitClient.getRetrofitInstance().create(PlageController.class);
        Call<List<Commentaire>> call = plageController.getAllCommentaire(nomPlage);
        call.enqueue(callback);
    }

    public void getCommentairesSite(String nomSite, Callback<List<Commentaire>> callback) {
        SiteController siteController = RetrofitClient.getRetrofitInstance().create(SiteController.class);
        Call<List<Commentaire>> call = siteController.getAllCommentaire(nomSite);
        call.enqueue(callback);
    }
}

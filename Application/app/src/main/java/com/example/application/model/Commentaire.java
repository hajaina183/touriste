package com.example.application.model;

import com.example.application.RetrofitClient;
import com.example.application.controler.ParcController;

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

    public void getCommentaires(String nomParc, Callback<List<Commentaire>> callback) {
        ParcController parcController = RetrofitClient.getRetrofitInstance().create(ParcController.class);
        Call<List<Commentaire>> call = parcController.getAllCommentaire(nomParc);
        call.enqueue(callback);
    }
}

package com.example.application.controler;

import com.example.application.model.EndroitPopulaire;
import com.example.application.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndroitPopulaireControler {

    @GET("parc")
    Call<List<EndroitPopulaire>>getAllData();
}

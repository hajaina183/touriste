package com.example.application.controler;

import com.example.application.model.EndroitPopulaire;
import com.example.application.model.Parc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParcController {

    @GET("parc")
    Call<List<Parc>> getAllParc();
}

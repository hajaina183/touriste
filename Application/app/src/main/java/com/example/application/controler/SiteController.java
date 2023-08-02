package com.example.application.controler;

import com.example.application.model.Parc;
import com.example.application.model.Site;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SiteController {

    @GET("site")
    Call<List<Site>> getAllSite();
}

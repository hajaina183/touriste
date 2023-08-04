package com.example.application.controler;

import com.example.application.model.Parc;
import com.example.application.model.Profil;
import com.example.application.model.Site;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface SiteController {

    @GET("site")
    Call<List<Site>> getAllSite();

    @FormUrlEncoded
    @PUT("site/insertCommentaireSite")
    Call<Profil> insertCommentaireSite(@Field("nom") String nom, @Field("date") Date date, @Field("text") String text, @Field("user") String user );
}

package com.example.application.controler;

import com.example.application.model.Parc;
import com.example.application.model.Plage;
import com.example.application.model.Profil;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface PlageController {

    @GET("plage")
    Call<List<Plage>> getAllPlages();

    @FormUrlEncoded
    @PUT("plage/insertCommentairePlage")
    Call<Profil> insertCommentairePlage(@Field("nom") String nom, @Field("date") Date date, @Field("text") String text, @Field("user") String user );
}

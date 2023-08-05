package com.example.application.controler;

import com.example.application.model.Commentaire;
import com.example.application.model.EndroitPopulaire;
import com.example.application.model.Parc;
import com.example.application.model.Profil;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ParcController {

    @GET("parc")
    Call<List<Parc>> getAllParc();

    @FormUrlEncoded
    @PUT("parc/insertCommentaireParc")
    Call<Profil> insertCommentaireParc(@Field("nom") String nom, @Field("date") Date date, @Field("text") String text,@Field("user") String user );

    @FormUrlEncoded
    @POST("parc/listeCommentaire")
    Call<List<Commentaire>> getAllCommentaire(@Field("nom") String nom);

}

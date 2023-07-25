package com.example.application.controler;

import com.example.application.model.Profil;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProfilControler {
    @FormUrlEncoded
    @POST("profil/traitementLogin")
    Call<Profil> traitementLogin(@Field("user") String user, @Field("mdp") String mdp);
}

package com.example.application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {

    @GET("employees")
    Call<List<Model>> getAllData();

    @FormUrlEncoded
    @POST("employees")
    Call<Model> getModelInformation(
            @Field("name") String name,
            @Field("position") String position,
            @Field("office") String office,
            @Field("salary") String salary
    );
}

package com.example.application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {

    @GET("employees")
    Call<List<Model>> getAllData();
}

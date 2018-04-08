package com.example.kaan.factapp.Connection;


import com.example.kaan.factapp.Model.Request.LoginRequest;
import com.example.kaan.factapp.Model.Response.LoginResponse;
import com.example.kaan.factapp.Model.Response.RegisterResponse;


import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterFactory {

    @GET("register.php")
    Call<RegisterResponse> registerAPI(
            @Query("email") String email,
            @Query("password") String password,
            @Query("name") String name,
            @Query("lastName") String lastName,
            @Query("phone") String phone,
            @Query("birthDate") String birthDate,
            @Query("gender") String gender);


    @GET("login.php")
    Call<LoginResponse> loginAPI(
            @Query("email") String email,
            @Query("password") String password);
}

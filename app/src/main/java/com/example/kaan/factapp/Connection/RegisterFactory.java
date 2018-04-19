package com.example.kaan.factapp.Connection;


import com.example.kaan.factapp.Model.Request.IncidentAllModel;
import com.example.kaan.factapp.Model.Request.LoginRequest;
import com.example.kaan.factapp.Model.Request.UploadRequest;
import com.example.kaan.factapp.Model.Response.LoginResponse;
import com.example.kaan.factapp.Model.Response.RegisterResponse;
import com.example.kaan.factapp.Model.Response.UploadResponse;
import com.example.kaan.factapp.Model.TotalEventModel;


import java.util.ArrayList;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @FormUrlEncoded
    @POST("upload.php")
    Call<UploadResponse> uploadAPI(
            @Field("dateTimes") String dateTimes,
            @Field("image") String image,
            @Field("image2") String image2,
            @Field("image3") String image3,
            @Field("name") String name,
            @Field("location") String location,
            @Field("userEmail") String userEmail,
            @Field("userName") String userName,
            @Field("userLastName") String userLastName,
            @Field("resultIncident") String resultIncident);

    @POST("list.php")
    Call<ArrayList<TotalEventModel>> listAPI();


    @POST("myList.php")
    Call<ArrayList<TotalEventModel>> myListAPI(@Body IncidentAllModel incidentAllModel);
}

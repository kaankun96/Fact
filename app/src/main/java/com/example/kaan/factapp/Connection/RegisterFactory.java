package com.example.kaan.factapp.Connection;


import com.example.kaan.factapp.Model.LocationModel;
import com.example.kaan.factapp.Model.Request.IncidentAllModel;
import com.example.kaan.factapp.Model.Request.LoginRequest;
import com.example.kaan.factapp.Model.Request.UploadRequest;
import com.example.kaan.factapp.Model.Response.LoginResponse;
import com.example.kaan.factapp.Model.Response.RegisterResponse;
import com.example.kaan.factapp.Model.Response.UpdateIncidentModel;
import com.example.kaan.factapp.Model.Response.UploadResponse;
import com.example.kaan.factapp.Model.TotalEventModel;


import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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


    @Multipart
    @POST("upload.php")
    Call<UploadResponse> uploadAPI(
            @Part("dateTimes")RequestBody dateTimes,
            @Part("name")RequestBody name,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part image2,
            @Part MultipartBody.Part image3,
            @Part("location")RequestBody location,
            @Part("userEmail")RequestBody userEmail,
            @Part("userName")RequestBody userName,
            @Part("userLastName")RequestBody userLastName,
            @Part("resultIncident")RequestBody resultIncident
    );

    @POST("list.php")
    Call<ArrayList<TotalEventModel>> listAPI();


    @POST("myList.php")
    Call<ArrayList<TotalEventModel>> myListAPI(@Body IncidentAllModel incidentAllModel);

    @POST("closeList.php")
    Call<ArrayList<TotalEventModel>> closeListAPI(@Body LocationModel locationModel);

    @GET("updateList.php")
    Call<UpdateIncidentModel> updateListAPI(@Query("id")int id, @Query("resultIncident") String resultIncident);
}

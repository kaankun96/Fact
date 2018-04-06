package com.example.kaan.factapp.Connection;


import com.example.kaan.factapp.Model.Request.RegisterRequest;
import com.example.kaan.factapp.Model.Response.RegisterResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterFactory {
    @POST("test/insert.php")
    Call<RegisterResponse> register(@Body RegisterRequest register);


}

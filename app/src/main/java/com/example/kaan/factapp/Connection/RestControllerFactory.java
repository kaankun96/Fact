package com.example.kaan.factapp.Connection;

import com.example.kaan.factapp.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestControllerFactory {

    public static final String BASE_URL = "http://192.168.64.2/factApp/";
    private static RestControllerFactory instance = new RestControllerFactory();
    private static final int timeoutInterval = 30;
    private static Retrofit factAppService = null;

    public static RestControllerFactory getInstance() {
        if (instance == null)
            instance = new RestControllerFactory();
        return instance;
    }

    private RegisterFactory registerFactory;

    private RestControllerFactory() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (factAppService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.connectTimeout(timeoutInterval, TimeUnit.SECONDS);
            httpClient.readTimeout(timeoutInterval, TimeUnit.SECONDS);
            factAppService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        registerFactory = factAppService.create(RegisterFactory.class);
    }

    public RegisterFactory getRegisterFactory() {
        return registerFactory;
    }


}

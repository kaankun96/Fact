package com.example.kaan.factapp.Connection;

import com.example.kaan.factapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestControllerFactory {

    private static RestControllerFactory instance = new RestControllerFactory();
    private static final int timeoutInterval = 30;

    private static Retrofit apiService = null;

    public static RestControllerFactory getInstance() {
        if (instance == null)
            instance = new RestControllerFactory();
        return instance;
    }

    private RegisterFactory registerFactory;

    private RestControllerFactory() {
        if (apiService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG_MODE) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            // Baglanti süreleri icin yazildi
            httpClient.connectTimeout(timeoutInterval, TimeUnit.SECONDS);
            httpClient.readTimeout(timeoutInterval, TimeUnit.SECONDS);
            apiService = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ADDRESS)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        registerFactory = apiService.create(RegisterFactory.class);

    }

    public RegisterFactory getRegisterFactory() {
        return registerFactory;
    }


}

package com.example.kaan.factapp;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.kaan.factapp.Utils.ClientPreferences;

public class FactApp extends MultiDexApplication {

    private static ClientPreferences clientPreferences;
    private static FactApp instance;
    private static Context context;

    @Override
    public void onCreate() {
        MultiDex.install(getBaseContext());
        FactApp.context = getApplicationContext();
        initApp();
        super.onCreate();
    }

    public static FactApp getInstance() {
        return instance;
    }

    public static ClientPreferences getClientPreferences() {
        return clientPreferences;
    }

    private void initApp() {
        instance = this;
        clientPreferences = new ClientPreferences(this);
    }

}

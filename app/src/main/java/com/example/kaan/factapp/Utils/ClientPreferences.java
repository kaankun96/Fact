package com.example.kaan.factapp.Utils;

import android.content.Context;

import com.example.kaan.factapp.Model.Request.LoginRequest;
import com.example.kaan.factapp.Model.Response.LoginResponse;

public final class ClientPreferences extends PreferencesManager {


    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String USER = "USER";
    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";


    public ClientPreferences(Context targetContext) {
        super(targetContext);
    }

    public LoginResponse getUser() {
        return getObject(USER, LoginResponse.class);
    }

    public void setUser(LoginResponse user) {
        putObject(USER, user);
    }

    public String getUsername() {
        return getString(USERNAME, "");
    }

    public String getPassword() {
        return getString(PASSWORD, "");
    }

    public void setUserNameAndPassword(String username, String password) {
        putString(USERNAME, username);
        putString(PASSWORD, password);
    }
    public boolean isLoggedIn() {
        return getBooleanValue(IS_LOGGED_IN, false);
    }

    public boolean setLoggedIn(boolean isLoggedIn) {
        return putBoolean(IS_LOGGED_IN, isLoggedIn);
    }
}
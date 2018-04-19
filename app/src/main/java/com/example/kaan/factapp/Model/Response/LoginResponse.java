package com.example.kaan.factapp.Model.Response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("Response")
    private String response;
    @SerializedName("isLoginSuccess")
    private boolean isLoginSuccess;
    @SerializedName("Email")
    private String email;
    @SerializedName("Password")
    private String password;
    @SerializedName("Name")
    private String name;
    @SerializedName("LastName")
    private String lastName;


    public LoginResponse(String response, boolean isLoginSuccess, String email, String password, String name, String lastName) {
        this.response = response;
        this.isLoginSuccess = isLoginSuccess;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

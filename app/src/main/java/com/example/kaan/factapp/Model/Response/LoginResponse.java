package com.example.kaan.factapp.Model.Response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("Response")
    private String response;
    @SerializedName("Name")
    private  String name;

    public LoginResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

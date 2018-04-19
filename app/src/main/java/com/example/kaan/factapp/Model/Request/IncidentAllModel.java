package com.example.kaan.factapp.Model.Request;

import com.google.gson.annotations.SerializedName;

public class IncidentAllModel {
    @SerializedName("userEmail")
    private String userEmail;

    public IncidentAllModel() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

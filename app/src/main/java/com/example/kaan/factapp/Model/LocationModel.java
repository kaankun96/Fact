package com.example.kaan.factapp.Model;

import com.google.gson.annotations.SerializedName;



public class LocationModel {

    @SerializedName("location")
    private String location;

    public LocationModel() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

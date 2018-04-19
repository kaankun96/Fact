package com.example.kaan.factapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 20.04.2018.
 */

public class LocationModel {
    @SerializedName("location")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

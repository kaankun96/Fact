package com.example.kaan.factapp.Model.Request;

import com.google.gson.annotations.SerializedName;

public class UploadRequest {
    @SerializedName("dateTimes")
    private String dateTimes;
    @SerializedName("image")
    private String url;
    @SerializedName("image2")
    private String url2;
    @SerializedName("image3")
    private String url3;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("userEmail")
    private String userEmail;
    @SerializedName("userName")
    private String userName;
    @SerializedName("userLastName")
    private String userLastName;
    @SerializedName("resultIncident")
    private String resultIncident;

    public UploadRequest() {
    }

    public String getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(String dateTimes) {
        this.dateTimes = dateTimes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getResultIncident() {
        return resultIncident;
    }

    public void setResultIncident(String resultIncident) {
        this.resultIncident = resultIncident;
    }
}

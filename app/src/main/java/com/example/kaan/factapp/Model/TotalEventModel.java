package com.example.kaan.factapp.Model;

public class TotalEventModel {
    private String incidentName,incidentLocation,incidentInformer,incidentDate;
    private Boolean incidentCondition;

    public TotalEventModel(String incidentName, String incidentLocation, String incidentInformer, String incidentDate, Boolean incidentCondition) {
        this.incidentName = incidentName;
        this.incidentLocation = incidentLocation;
        this.incidentInformer = incidentInformer;
        this.incidentDate = incidentDate;
        this.incidentCondition = incidentCondition;
    }

    public String getIncidentName() {
        return incidentName;
    }

    public void setIncidentName(String incidentName) {
        this.incidentName = incidentName;
    }

    public String getIncidentLocation() {
        return incidentLocation;
    }

    public void setIncidentLocation(String incidentLocation) {
        this.incidentLocation = incidentLocation;
    }

    public String getIncidentInformer() {
        return incidentInformer;
    }

    public void setIncidentInformer(String incidentInformer) {
        this.incidentInformer = incidentInformer;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Boolean getIncidentCondition() {
        return incidentCondition;
    }

    public void setIncidentCondition(Boolean incidentCondition) {
        this.incidentCondition = incidentCondition;
    }
}

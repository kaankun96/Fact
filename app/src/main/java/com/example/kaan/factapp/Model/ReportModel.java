package com.example.kaan.factapp.Model;

public class ReportModel {
    private String incidentName,incidentLocation;
    private boolean incidentCondition;

    public ReportModel(String incidentName, String incidentLocation, boolean incidentCondition) {
        this.incidentName = incidentName;
        this.incidentLocation = incidentLocation;
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

    public boolean getIncidentCondition() {
        return incidentCondition;
    }

    public void setIncidentCondition(boolean incidentCondition) {
        this.incidentCondition = incidentCondition;
    }
}

package com.example.nullproject2.enumerations;

public enum PatientStatus {

    AVAILABLE("AVAILABLE"),
    PENTING("PENTING"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private String status;

    PatientStatus(String status) { this.status = status; }

    public String getStatus() {return status;}

}

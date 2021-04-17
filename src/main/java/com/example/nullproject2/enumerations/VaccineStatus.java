package com.example.nullproject2.enumerations;

public enum VaccineStatus {
    UNAVAILABLE("UNAVAILABLE"),
    AVAILABLE("AVAILABLE");

    private String vaccineStatus;

    VaccineStatus(String status) { this.vaccineStatus = status; }

    public String getVaccineStatus() {return vaccineStatus;}
}

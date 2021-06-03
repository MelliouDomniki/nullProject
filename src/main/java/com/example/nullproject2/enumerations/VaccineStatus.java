package com.example.nullproject2.enumerations;

public enum VaccineStatus {
    UNAVAILABLE("UNAVAILABLE"),
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED");

    private String vaccineStatus;

    VaccineStatus(String status) { this.vaccineStatus = status; }

    public String getVaccineStatus() {return vaccineStatus;}
}

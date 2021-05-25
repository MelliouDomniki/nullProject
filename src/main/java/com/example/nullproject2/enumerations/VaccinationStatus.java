package com.example.nullproject2.enumerations;

public enum VaccinationStatus {

    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    DONE("DONE");

    private String vaccinationStatus;

    VaccinationStatus(String status){ this.vaccinationStatus = status; }

    public String getVaccinationStatus(){return vaccinationStatus;}

}

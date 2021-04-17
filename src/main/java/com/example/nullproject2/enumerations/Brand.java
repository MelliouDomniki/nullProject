package com.example.nullproject2.enumerations;

public enum Brand {

    PFIZER("Pfizer"),
    ASTRAZENECA("AstraZeneca"),
    NOVAVAX("Novavax"),
    MODERNA("Moderna"),
    JOHNSON("Johnson & Johnson's Janssen");

    private String brand;

    Brand(String brand) { this.brand = brand; }

    public String getBrand() {return brand;}

}

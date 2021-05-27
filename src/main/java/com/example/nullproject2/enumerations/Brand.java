package com.example.nullproject2.enumerations;

import java.util.Arrays;
import java.util.List;

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

package com.example.nullproject2.enumerations;

public enum Sex {

    MALE("Male"),
    FEMALE("Female");

    private String sex;

    Sex(String sex) { this.sex = sex; }

    public String getSex() {return sex;}
}

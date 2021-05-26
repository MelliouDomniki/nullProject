package com.example.nullproject2.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PatientForm {

    private static final String AMKA_PATTERN = "^[0-9]*$";

    private static final int AMKA_MIN_SIZE = 11;
    private static final int AMKA_MAX_SIZE = 11;

    @Pattern(regexp = AMKA_PATTERN, message = "Please use only numbers")
    @Size(min = AMKA_MIN_SIZE, max = AMKA_MAX_SIZE, message = "AMKA must be 11 characters")
    @NotEmpty
    private String amka;

    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int age;

    @NotEmpty
    private String address;

    @NotEmpty
    private String status;

    @NotEmpty
    private String symptoms;

    @NotEmpty
    private String sex;




}

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
    private static final int PATIENT_ID_MIN_SIZE = 1;
    private static final int PATIENT_ID_MAX_SIZE = 6;

    @Pattern(regexp = AMKA_PATTERN, message = "Please use only numbers")
    @Size(min = AMKA_MIN_SIZE, max = AMKA_MAX_SIZE, message = "AMKA must be 11 characters")
    @NotEmpty
    private String amka;

    @Size(min = PATIENT_ID_MIN_SIZE, max = PATIENT_ID_MAX_SIZE)
    @NotEmpty
    private String patient_id;


    private String id;
    private String name;
    private String age;
    private String address;
    private String status;
    private String symptoms;
    private String sex;


}

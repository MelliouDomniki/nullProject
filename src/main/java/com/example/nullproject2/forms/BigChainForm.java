package com.example.nullproject2.forms;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class BigChainForm {

    private static final String AMKA_PATTERN = "^[0-9]*$";
    private static final int AMKA_MIN = 11;
    private static final int AMKA_MAX = 11;

    @Pattern(regexp = AMKA_PATTERN, message = "Please use only numbers")
    @Size(min = AMKA_MIN, max = AMKA_MAX, message = "AMKA must be 11 characters")
    @NotEmpty
    private String amka;

    @NotEmpty
    private String id;

    @NotEmpty
    private String hospital;

    @NotEmpty
    private String patient;

    @NotEmpty
    private int age;

    @NotEmpty
    private String brand;

    @NotEmpty
    private Date date;

    @NotEmpty
    private String patientStatus;

    @NotEmpty
    private String city;

    @NotEmpty
    private String country;
}

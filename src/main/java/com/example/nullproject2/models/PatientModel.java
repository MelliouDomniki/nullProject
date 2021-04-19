package com.example.nullproject2.models;


import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientModel {


    private int patient_id;
    private String name;
    private int age;
    private String address;
    private PatientStatus status;
    private String symptoms;
    private String amka;
    private Sex sex;

}

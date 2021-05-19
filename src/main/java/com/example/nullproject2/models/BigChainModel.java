package com.example.nullproject2.models;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BigChainModel {

    private String id;
    private String hospital;
    private String patient;
    private String amka;
    private int age;
    private Brand brand;
    private Date date;
    private PatientStatus patientStatus;
    private String city;
    private String country;
}

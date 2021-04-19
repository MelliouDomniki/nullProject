package com.example.nullproject2.entity;


import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patient_id;

    @Field(name = "name")
    private String name;

    @Field(name = "age")
    private int age;

    @Field(name = "address")
    private String address;

    @Field(name = "status")
    private PatientStatus status;

    @Field(name = "symptoms")
    private String symptoms;

    @Field(name = "amka")
    private String amka;

    @Field(name = "sex")
    private Sex sex;

}

package com.example.nullproject2.entity;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "Bigchain")
public class BigChain {

    @Id
    private String id;

    @Field(name = "hospital")
    private String hospital;

    @Field(name = "patient")
    private String patient;

    @Field(name = "amka")
    private String amka;

    @Field(name = "age")
    private int age;

    @Field(name = "brand")
    private Brand brand;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Field(name = "date")
    private Date date;

    @Field(name = "status")
    private PatientStatus patientStatus;

    @Field(name = "city")
    private String city;

    @Field(name = "country")
    private String country;
}

package com.example.nullproject2.entity;


import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import io.github.kaiso.relmongo.annotation.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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

    @ManyToOne(mappedBy = "patients")
    private User user;

    @DBRef
    private List<Vaccine> vaccines;

}

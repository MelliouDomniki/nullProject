package com.example.nullproject2.entity;


import com.example.nullproject2.enumerations.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Patients")
public class Patient {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "age")
    private int age;

    @Field(name = "address")
    private String address;

    @Field(name = "status")
    private String status;

    @Field(name = "symptoms")
    private List<String> symptoms;

    @Field(name = "amka")
    private String amka;

    @Field(name = "sex")
    private Sex sex;

    @DBRef
    private List<Vaccine> vaccines = new ArrayList<>();

    public Patient(String name,int age, String address, String status, String amka, Sex sex)
    {
        this.name =name;
        this.age=age;
        this.address=address;
        this.status=status;
        this.amka = amka;
        this.sex = sex;
    }

}

package com.example.nullproject2.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document (collection = "Hospitals")
public class Hospital {


    @Id
    @Field(name = "id")
    private String id;

    @Field(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospital_id;

    @Field(name = "name")
    private String name;

    @Field(name = "address")
    private String address;

    @Field(name = "phone_number")
    private String phone_number;

    @Field(name = "city")
    private String city;

    @Field(name = "country")
    private String country;

    @Field(name = "available_doses")
    private int available_doses;

    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

}

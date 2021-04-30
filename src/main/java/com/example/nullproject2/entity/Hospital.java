package com.example.nullproject2.entity;
import com.example.nullproject2.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Hospitals")
public class Hospital {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String hospital_id;

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

    @DBRef
    private List<Patient> patients;

    @DBRef
    private List<Vaccine> vaccines;
}

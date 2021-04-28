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

    @Field(name = "email")
    private String email;

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public Hospital(String hospital_id, String name, String address, String phone_number, String city, String country, int available_doses, String email, String username, String password, String user) {
        this.hospital_id = hospital_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.country = country;
        this.available_doses = available_doses;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = getRoles();
    }
}

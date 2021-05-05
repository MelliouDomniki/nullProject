package com.example.nullproject2.entity;

import com.example.nullproject2.roles.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

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

    @Field(name = "username")
    private String username;

    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();


    public User(String name, String address, String phone_number, String city, String country, int available_doses, String username, String email, String password) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.country = country;
        this.available_doses = available_doses ;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
package com.example.nullproject2.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalModel {


    private String hospital_id;
    private String name;
    private String address;
    private String phone_number;
    private String city;
    private String country;
    private int available_doses;
    private String email;
    private String username;
    private String password;


}

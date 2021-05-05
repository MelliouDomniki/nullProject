package com.example.nullproject2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String user_id;
    private String name;
    private String address;
    private String phone_number;
    private String city;
    private String country;
    private int available_doses;
    private String username;
    private String email;
    private String password;

}
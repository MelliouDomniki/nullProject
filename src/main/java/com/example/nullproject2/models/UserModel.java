package com.example.nullproject2.models;

import com.example.nullproject2.enumerations.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String id;
    private String name;
    private String address;
    private String phone_number;
    private String city;
    private String country;
    private int availableDoses;
    private String username;
    private String email;
    private String password;
    private UserStatus transactionStatus;
}

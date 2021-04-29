package com.example.nullproject2.payload;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private String password;
    private List<String> roles;


    public JwtResponse(String accessToken, String username, String password, List<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }



}

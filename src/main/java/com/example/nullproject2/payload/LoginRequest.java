package com.example.nullproject2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {


    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}

package com.example.nullproject2.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupRequest {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$";

    private static final int EMAIL_MIN_SIZE = 3;
    private static final int EMAIL_MAX_SIZE = 50;
    private static final int PASSWORD_MIN_SIZE = 6;
    private static final int PASSWORD_MAX_SIZE = 40;
    private static final int USERNAME_MIN_SIZE = 6;
    private static final int USERNAME_MAX_SIZE = 25;


    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotEmpty(message = "Address can't be empty")
    private String address;

    @NotEmpty(message = "Phone Number can't be empty")
    private String phone_number;

    @NotEmpty(message = "City can't be empty")
    private String city;

    @NotEmpty(message = "Country can't be empty")
    private String country;

    @NotEmpty(message = "Available doses can't be empty")
    private int availableDoses;

    @Size(min = USERNAME_MIN_SIZE, max = USERNAME_MAX_SIZE, message = "username must be between 6 and 25 characters")
    @NotEmpty(message = "Username can't be empty")
    private String username;

    @Pattern(regexp = EMAIL_PATTERN, message = "Email pattern doesn't match")
    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE, message = "Email must be between 3 and 40 characters")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "Your password must be 4 to 15")
    @NotEmpty(message = "Password can't be empty")
    private String password;

    private Set<String> roles;
}

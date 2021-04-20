package com.example.nullproject2.forms;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class HospitalForm {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$";
    private static final String PHONE_NUMBER_PATTERN = "^[0-9]$";
    private static final String HOSPITAL_ID_PATTERN = "^[0-9]$";

    private static final int EMAIL_MIN_SIZE = 3;
    private static final int EMAIL_MAX_SIZE = 255;
    private static final int PHONE_NUMBER_MIN_SIZE = 10;
    private static final int PHONE_NUMBER_MAX_SIZE = 10;
    private static final int HOSPITAL_ID_MIN_SIZE = 1;
    private static final int HOSPITAL_ID_MAX_SIZE = 4;
    private static final int PASSWORD_MIN_SIZE = 4;
    private static final int PASSWORD_MAX_SIZE = 15;


    @Pattern(regexp = EMAIL_PATTERN, message = "Email pattern doesn't match")
    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE, message = "Email must be between 3 and 40 characters")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @Pattern(regexp = HOSPITAL_ID_PATTERN, message = "Hospital ID pattern doesn't match")
    @Size(min = HOSPITAL_ID_MIN_SIZE, max = HOSPITAL_ID_MAX_SIZE, message = "Hospital ID length must be between 1 to 4 numbers")
    @NotEmpty(message = "Hospital ID can't be empty")
    private String hospitalId;

    @Pattern(regexp = PHONE_NUMBER_PATTERN, message  = "Phone number pattern doesn't match")
    @Size(min = PHONE_NUMBER_MIN_SIZE, max = PHONE_NUMBER_MAX_SIZE, message = "Phone number length must be 10")
    @NotEmpty(message = "Phone Number can't be empty")
    private String phoneNumber;

    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE, message = "Your password must be 4 to 15")
    @NotEmpty(message = "Password can't be empty")
    private String password;


    private String id;
    private String name;
    private String address;
    private String city;
    private String country;
    private int available_doses;


}

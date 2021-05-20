package com.example.nullproject2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.util.Date;

@Data
@NoArgsConstructor
public class Appointment {

    @Id
    private String id;

    private User hospital;

    private Patient patient;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private Vaccine vaccine;
}

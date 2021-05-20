package com.example.nullproject2.entity;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
public class BigChain {

    @Id
    private String id;

    private String hospital_id;
    private Patient patient_AMKA;
    private Vaccine vaccine_brand;
    private Date date;

}

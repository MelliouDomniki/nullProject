package com.example.nullproject2.entity;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigChain {

    @Id
    private String id;

    @Field
    private String hosp;

    @Field
    private String AMKA;

    @Field
    private String brand;

    @Field
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

}

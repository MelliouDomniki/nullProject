package com.example.nullproject2.entity;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
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
@Document (collection = "Vaccines")
public class Vaccine {

    @Id
    private String id;

    @Field(name = "brand")
    private Brand brand;

    @Field(name = "status")
    private VaccineStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Field(name = "date")
    private Date date;

    @Field(name = "hospital")
    private String hospitalName;

    public Vaccine(Brand b, VaccineStatus s, Date d, String hospital) {
        this.brand=b;
        this.status=s;
        this.date=d;
        this.hospitalName = hospital;

    }
}

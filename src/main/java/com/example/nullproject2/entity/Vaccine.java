package com.example.nullproject2.entity;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.kaiso.relmongo.annotation.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vaccine_id;

    @Field(name = "brand")
    private Brand brand;

    @Field(name = "status")
    private VaccineStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Field(name = "date")
    private Date date;

    public Vaccine(Brand brand, VaccineStatus status, Date date) {
        this.brand = brand;
        this.status = status;
        this.date = date;
    }
}

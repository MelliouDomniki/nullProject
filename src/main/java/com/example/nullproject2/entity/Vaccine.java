package com.example.nullproject2.entity;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Data
@AllArgsConstructor
@Document (collection = "Vaccines")
public class Vaccine {

    @Id
    private String vaccine_id;

    @Field(name = "brand")
    private Brand brand;

    @Field(name = "status")
    private VaccineStatus status;

    @Field(name = "date")
    private Date date;



    @Override
    public String toString() {
        return "Vaccine{" +
                "vaccine_id='" + vaccine_id + '\'' +
                ", brand=" + brand +
                ", status=" + status +
                '}';
    }

}

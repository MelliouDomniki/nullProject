package com.example.nullproject2.models;


import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineModel {
    private String id;
    private Brand brand;
    private VaccineStatus status;
    private Date date;
}

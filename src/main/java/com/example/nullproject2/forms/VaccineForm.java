package com.example.nullproject2.forms;


import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class VaccineForm {

    private static final int CODE_MIN_SIZE = 4;
    private static final int CODE_MAX_SIZE = 5;



    @NotEmpty
    private String id;
    private String brand;
    private String status;
    private Date date;


}

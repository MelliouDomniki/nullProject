package com.example.nullproject2.forms;

import com.example.nullproject2.enumerations.VaccineStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class VaccineForm {

    private static final int CODE_MIN_SIZE = 4;
    private static final int CODE_MAX_SIZE = 5;

//    @Size(min = CODE_MAX_SIZE, max = CODE_MAX_SIZE, message = "Vaccine code must be between 4 and 5 numbers")
//  private String code;

    @NotEmpty
    private String id;
    private String brand;
    private String status;
    private Date date;


}

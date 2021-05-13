package com.example.nullproject2.mappers;


import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.forms.VaccineForm;
import org.springframework.stereotype.Component;

@Component
public class VaccineFormMapper {


    public static Vaccine mapToVaccine(VaccineForm vaccineForm) {
        if (vaccineForm == null)
            return null;

        Vaccine vaccine = new Vaccine();

        vaccine.setId(vaccineForm.getId());

        if (vaccineForm.getBrand() != null) {
            if (vaccineForm.getBrand().isBlank()){
                vaccineForm.setBrand(vaccineForm.getBrand());
            }
        }

        if (vaccineForm.getStatus() != null) {
            if (vaccineForm.getStatus().isBlank()){
                vaccineForm.setStatus(vaccineForm.getStatus());
            }
        }

        vaccineForm.setDate(vaccineForm.getDate());



        return vaccine;
    }

    public static VaccineForm mapToVaccineForm(Vaccine vaccine){
        if(vaccine == null)
            return null;

        VaccineForm vaccineForm = new VaccineForm();

        vaccineForm.setId(vaccine.getId());
        vaccineForm.setBrand(String.valueOf(vaccine.getBrand()));
        vaccineForm.setStatus(String.valueOf(vaccine.getStatus()));
        vaccineForm.setDate(vaccine.getDate());

        return vaccineForm;

    }
}

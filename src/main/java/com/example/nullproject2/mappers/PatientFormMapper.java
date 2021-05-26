package com.example.nullproject2.mappers;


import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import org.springframework.stereotype.Component;

@Component
public class PatientFormMapper {

    public static Patient mapToPatient(PatientForm patientForm){
        if(patientForm == null)
            return null;

        Patient patient = new Patient();

        patient.setId(patientForm.getId());

        if(patientForm.getName() != null){
            if(patientForm.getName().isBlank()){
                patient.setName(patientForm.getName());
            }
        }

        patient.setAge(patientForm.getAge());


        if(patientForm.getAddress() != null){
            if(patientForm.getAddress().isBlank()){
                patient.setAddress(patientForm.getAddress());
            }
        }

        if(patientForm.getStatus() != null){
            if(patientForm.getStatus().isBlank()){
                patient.setStatus(patientForm.getStatus());
            }
        }


        if(patientForm.getSymptoms() != null){
            if(patientForm.getSymptoms().isBlank()){
                patient.setSymptoms(patientForm.getSymptoms());
            }
        }

        if(patientForm.getAmka() != null){
            if(patientForm.getAmka().isBlank()){
                patient.setAmka(patientForm.getAmka());
            }
        }

        if(patientForm.getSex() != null){
            if(patientForm.getSex().isBlank()){
                patient.setSex(Sex.valueOf(patientForm.getSex()));
            }
        }


        return patient;
    }

    public static PatientForm mapToPatientForm(Patient patient){
        if(patient == null)
            return null;

        PatientForm patientForm = new PatientForm();

        patientForm.setId(patient.getId());
        patientForm.setName(patient.getName());
        patientForm.setAge(patient.getAge());
        patientForm.setAddress(patient.getAddress());
        patientForm.setStatus(patient.getStatus());
        patientForm.setSymptoms(patient.getSymptoms());
        patientForm.setAmka(patient.getAmka());
        patientForm.setSex(String.valueOf(patient.getSex()));

        return patientForm;
    }




}
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
        patient.setName(patientForm.getName());
        patient.setAge(patientForm.getAge());
        patient.setAddress(patientForm.getAddress());
        patient.setStatus(patientForm.getStatus());
        patient.setSymptoms(patientForm.getSymptoms());
        patient.setAmka(patientForm.getAmka());
        patient.setSex(Sex.valueOf(patientForm.getSex()));



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
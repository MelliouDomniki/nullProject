package com.example.nullproject2.mappers;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.models.PatientModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientModel mapToPatientModel(Patient patient){
        if (patient == null) return null;
        PatientModel patientModel = new PatientModel();
        patientModel.setPatient_id(patient.getId());
        patientModel.setName(patient.getName());
        patientModel.setAge(patient.getAge());
        patientModel.setAddress(patient.getAddress());
        patientModel.setStatus(patient.getStatus());
        patientModel.setSymptoms(patient.getSymptoms());
        patientModel.setAmka(patient.getAmka());
        patientModel.setSex(patient.getSex());
        return patientModel;
    }

    public static List<PatientModel> mapToPatientModelList( List<Patient> patients){
        return patients.stream()
                .map(PatientMapper::mapToPatientModel)
                .collect(Collectors.toList());
    }

    public static Optional<PatientModel> mapToPatientModelOptional(Patient patient){
        if (patient == null) return Optional.empty();
        return Optional.of(mapToPatientModel(patient));
    }
}
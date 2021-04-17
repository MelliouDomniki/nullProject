package com.example.nullproject2.services;


import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<PatientModel> getAllPatients();

    Optional<PatientModel> getPatientById(String id);

    Optional<PatientModel> getPatientByPatientId(int patientId);

    Optional<PatientModel> addPatient (PatientForm patient) throws Exception;

    Optional<PatientModel> updatePatient (PatientForm toBeUpdatedPatient);

    List<PatientModel> getPatientByStatus (PatientStatus patientStatus);

    List<PatientModel> getPatientBySex (Sex sex);

    boolean deletePatientById (String id);

    boolean deletePatientByPatientId (int patientId);

    List<PatientModel> getPatientByName (String name);

    List<PatientModel> getPatientByAmka (long amka);




}

package com.example.nullproject2.services;


import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface PatientService {

    List<PatientModel> getAllPatients();

    Optional<PatientModel> getPatientByPatientId(String patient_id);

    Optional<PatientModel> addPatient (PatientForm patient) throws Exception;

    Optional<PatientModel> updatePatient (PatientForm toBeUpdatedPatient);

    List<PatientModel> getPatientByStatus (PatientStatus patientStatus);

    List<PatientModel> getPatientBySex (Sex sex);

    boolean deletePatientById (String patient_id);

    List<PatientModel> getPatientByName (String name);

    List<PatientModel> getPatientByAmka (long amka);

    List<Patient> getAllPatientsWithPagination(int pages, int size);

    List<Patient> getAllPatientsWithSortingByName();
}

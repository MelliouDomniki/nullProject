package com.example.nullproject2.services;

import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;
import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService{
    @Override
    public List<PatientModel> getAllPatients() {
        return null;
    }

    @Override
    public Optional<PatientModel> getPatientById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<PatientModel> getPatientByPatientId(int patientId) {
        return Optional.empty();
    }

    @Override
    public Optional<PatientModel> addPatient(PatientForm patient) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<PatientModel> updatePatient(PatientForm toBeUpdatedPatient) {
        return Optional.empty();
    }

    @Override
    public List<PatientModel> getPatientByStatus(PatientStatus patientStatus) {
        return null;
    }

    @Override
    public List<PatientModel> getPatientBySex(Sex sex) {
        return null;
    }

    @Override
    public boolean deletePatientById(String id) {
        return false;
    }

    @Override
    public boolean deletePatientByPatientId(int patientId) {
        return false;
    }

    @Override
    public List<PatientModel> getPatientByName(String name) {
        return null;
    }

    @Override
    public List<PatientModel> getPatientByAmka(long amka) {
        return null;
    }
}

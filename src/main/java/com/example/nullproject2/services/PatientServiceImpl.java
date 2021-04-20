package com.example.nullproject2.services;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;
import com.example.nullproject2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patrepo;

    @Override
    public List<PatientModel> getAllPatients() {
        return null;
    }

    @Override
    public Optional<PatientModel> getPatientByPatientId(String patient_id) {
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
    public boolean deletePatientById(String patient_id) {
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

    @Override
    public List<Patient> getAllPatientsWithPagination(int pages, int size)
    {
        Pageable pageable = PageRequest.of(pages-1, size);
        return patrepo.findAll(pageable).getContent();
    }
    @Override
    public List<Patient> getAllPatientsWithSortingByName()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return patrepo.findAll(sort);
    }
}

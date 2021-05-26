package com.example.nullproject2.services;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.mappers.PatientFormMapper;
import com.example.nullproject2.mappers.PatientMapper;
import com.example.nullproject2.models.PatientModel;
import com.example.nullproject2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patrepo;


    @Override
    public Optional<PatientModel> findByName(String name) {
        return PatientMapper.mapToPatientModelOptional(patrepo.findByName(name));
    }

    @Override
    public Optional<PatientModel> findFirstById(String id) {
        return PatientMapper.mapToPatientModelOptional(patrepo.findFirstById(id));
    }

    @Override
    public Optional<PatientModel> findFirstByAmka(String amka) {
        return PatientMapper.mapToPatientModelOptional(patrepo.findFirstByAmka(amka));
    }

    @Override
    public Optional<PatientModel> updateVaccine(PatientForm toBeUpdatedPatient) {
        Patient patient = PatientFormMapper.mapToPatient(toBeUpdatedPatient);
        Patient originalPatient = patrepo.findFirstByAmka(toBeUpdatedPatient.getAmka());

        if (patient.equals(originalPatient)) {
            // if no changes made dont update
            return PatientMapper.mapToPatientModelOptional(patient);
        }
        return PatientMapper.mapToPatientModelOptional(patrepo.save(patient));
    }

    @Override
    public List<PatientModel> findAll() {
        return PatientMapper.mapToPatientModelList(patrepo.findAll());
    }

    @Override
    public List<PatientModel> findByNameAndAge(String name, int age) {
        return PatientMapper.mapToPatientModelList(patrepo.findByNameAndAge(name, age));
    }

    @Override
    public List<PatientModel> findByNameIsLike(String name) {
        return PatientMapper.mapToPatientModelList(patrepo.findByNameIsLike(name));
    }

    @Override
    public List<PatientModel> findByNameStartsWith(String name) {
        return PatientMapper.mapToPatientModelList(patrepo.findByNameStartsWith(name));
    }

    @Override
    public boolean existsByAmka(String amka) {
        if (amka == null || findFirstByAmka(amka).isEmpty()){
            System.out.println("Patient not found");
            return false;
        }
        patrepo.existsByAmka(amka);
        return true;
    }

    @Override
    public boolean deleteByAmka(String amka) {
        if (amka == null || findFirstByAmka(amka).isEmpty()){
            System.out.println("Patient not found");
            return false;
        }
        patrepo.deleteByAmka(amka);
        return true;
    }

    @Override
    public Page<PatientModel> getAllPatientsWithPagination(int pages, int size) {
        Page<Patient> patientAsPage = patrepo.findAll(PageRequest.of(pages,size));
        if (patientAsPage.isEmpty()) return Page.empty();

        List<PatientModel> patientModels = PatientMapper.mapToPatientModelList(patientAsPage.getContent());
        return new PageImpl(patientModels, patientAsPage.getPageable(), patientAsPage.getTotalElements());
    }

    @Override
    public List<PatientModel> getAllPatientsWithSortingByName() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return PatientMapper.mapToPatientModelList(patrepo.findAll(sort));
    }
}

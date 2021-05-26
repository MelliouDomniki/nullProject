package com.example.nullproject2.services;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.enumerations.UserStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;
import com.example.nullproject2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patrepo;

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

    @Override
    public Optional<Patient> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> findFirstById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> findFirstByAmka(String AMKA) {
        return Optional.empty();
    }

    @Override
    public List<Patient> findByNameAndAge(String name, int age) {
        return null;
    }

    @Override
    public List<Patient> findByNameOrAge(String name, int age) {
        return null;
    }

    @Override
    public List<Patient> findByNameIsLike(String name) {
        return null;
    }

    @Override
    public List<Patient> findByNameStartsWith(String name) {
        return null;
    }

    @Override
    public Boolean existsByAmka(String amka) {
        return null;
    }
}

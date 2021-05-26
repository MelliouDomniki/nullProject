package com.example.nullproject2.services;


import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;


import java.util.List;
import java.util.Optional;


public interface PatientService {

    Optional<Patient> findByName(String name);

    Optional<Patient> findFirstById (String id);

    Optional<Patient> findFirstByAmka(String AMKA);

    List<Patient> findByNameAndAge(String name, int age);

    List<Patient> findByNameOrAge(String name, int age);

    List<Patient> findByNameIsLike (String name);

    List<Patient> findByNameStartsWith (String name);

    Boolean existsByAmka(String amka);

    List<Patient> getAllPatientsWithPagination(int pages, int size);

    List<Patient> getAllPatientsWithSortingByName();

}

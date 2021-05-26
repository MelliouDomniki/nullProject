package com.example.nullproject2.services;


import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.forms.PatientForm;
import com.example.nullproject2.models.PatientModel;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;


public interface PatientService {

    Optional<PatientModel> findByName(String name);

    Optional<PatientModel> findFirstById (String id);

    Optional<PatientModel> findFirstByAmka(String amka);

    List<PatientModel> findAll();

    List<PatientModel> findByNameAndAge(String name, int age);

    List<PatientModel> findByNameIsLike (String name);

    List<PatientModel> findByNameStartsWith (String name);

    boolean existsByAmka(String amka);

    boolean deleteByAmka(String amka);

    Page<PatientModel> getAllPatientsWithPagination(int pages, int size);

    List<PatientModel> getAllPatientsWithSortingByName();

}

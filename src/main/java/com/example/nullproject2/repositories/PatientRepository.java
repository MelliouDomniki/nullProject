package com.example.nullproject2.repositories;


import com.example.nullproject2.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository <Patient, String> {

    List<Patient> findByName(String name);
    List<Patient> findByNameAndAge(String name, int age);
    List<Patient> findByNameOrAge(String name, int age);
    List<Patient> findByNameIsLike (String name);
    List<Patient> findByNameStartsWith (String name);
    Boolean existsByAmka(String amka);

//    @Query()
//    List<Patient> findAllPatientsByUsername(String username);
}

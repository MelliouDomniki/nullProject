package com.example.nullproject2.repositories;


import com.example.nullproject2.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository <Patient, String> {

    Patient findByName(String name);
    Patient findFirstById (String id);
    Patient findFirstByAmka(String AMKA);
    List<Patient> findByNameAndAge(String name, int age);
    List<Patient> findByNameOrAge(String name, int age);
    List<Patient> findByNameIsLike (String name);
    List<Patient> findByNameStartsWith (String name);
    List<Patient> findByHospitalNameIsNullAndHospitalName (String name);
    boolean existsByAmka(String amka);
    boolean deleteByAmka(String amka);


}

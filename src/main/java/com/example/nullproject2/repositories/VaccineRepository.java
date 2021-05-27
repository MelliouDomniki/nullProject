package com.example.nullproject2.repositories;


import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends MongoRepository<Vaccine, String> {


    Vaccine findFirstById(String id);

    List<Vaccine> findByHospitalNameAndBrand(String username ,Brand brand);

    List<Vaccine> findByHospitalNameAndStatus(String username,VaccineStatus vaccineStatus);

    List<Vaccine> findByHospitalName(String string);

    List<Vaccine> findByHospitalNameAndBrandAndStatus(String username, Brand brand, VaccineStatus vaccineStatus);

    Vaccine findFirstByHospitalNameAndBrandAndStatus(String username, Brand brand, VaccineStatus vaccineStatus);

    int countByHospitalNameAndAndBrandAndStatus(String username,Brand brand, VaccineStatus vaccineStatus);
}

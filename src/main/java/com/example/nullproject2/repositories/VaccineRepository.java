package com.example.nullproject2.repositories;


import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends MongoRepository<Vaccine, String> {

    @Query("{ 'brand' : { $regex: ?0} }")
    List<Vaccine> findByBrand(Brand brand);

    @Query("{ 'status' : { $regex: ?0} }")
    List<Vaccine> findByStatus(VaccineStatus vaccineStatus);


}

package com.example.nullproject2.repositories;

import com.example.nullproject2.entity.BigChain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigChainRepository extends MongoRepository<BigChain, String> {

    List<BigChain> findByAge(int age);

    @Query("{'hospital' : { $regex: ?0} }")
    List<BigChain> findByHospital(String hospital);

    @Query("{'city' : { $regex: ?0} }")
    List<BigChain> findByCity(String city);

    @Query("{'country' : { $regex: ?0} }")
    List<BigChain> findByCountry(String country);
}

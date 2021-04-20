package com.example.nullproject2.repositories;

import com.example.nullproject2.entity.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalRepository extends MongoRepository <Hospital, String> {

        List<Hospital> findByName(String name);
}

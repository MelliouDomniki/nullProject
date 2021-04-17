package com.example.nullproject2.repositories;

import com.example.nullproject2.entity.Vaccine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VaccineRepository extends MongoRepository<Vaccine, String> {
}

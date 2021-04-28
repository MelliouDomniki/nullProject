package com.example.nullproject2.repositories;

import com.example.nullproject2.entity.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Hospital, String> {

    Optional<Hospital> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}

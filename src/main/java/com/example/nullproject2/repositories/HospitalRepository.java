package com.example.nullproject2.repositories;

import com.example.nullproject2.entity.Hospital;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HospitalRepository extends MongoRepository <Hospital, String> {

        List<Hospital> findByName(String name);

        Optional<User> findByUsername(String username);

        Boolean existsByUsername(String username);

        Boolean existsByEmail(String email);
}

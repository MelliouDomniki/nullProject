package com.example.nullproject2.repositories;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByName(String name);

    @Query("{ 'username' : ?0 }")
    Optional<User> getHospital(String username);

}

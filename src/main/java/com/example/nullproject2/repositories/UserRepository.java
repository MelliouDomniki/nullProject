package com.example.nullproject2.repositories;


import com.example.nullproject2.entity.User;
import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByName(String name);

    boolean deleteByUsername(String username);

    @Query("{ 'username' : ?0 }")
    User getHospital(String username);
}

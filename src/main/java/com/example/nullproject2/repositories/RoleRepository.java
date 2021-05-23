package com.example.nullproject2.repositories;

import com.example.nullproject2.roles.Erole;
import com.example.nullproject2.roles.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(Erole name);
}

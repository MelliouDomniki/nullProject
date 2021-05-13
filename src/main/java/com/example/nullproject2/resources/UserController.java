package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.repositories.UserRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getHospitalAttributesById/{username}")
    public Optional<User> getHospital(@PathVariable String username) {
        return userRepository.getHospital(username);
    }
}

package com.example.nullproject2.resources;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.repositories.UserRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

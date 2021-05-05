package com.example.nullproject2.resources;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.repositories.UserRepository;
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


    @GetMapping("/getHospitalById/{id}")
    public Optional<User> getHospital(@PathVariable String id) {return userRepository.getHospital(id); }
}

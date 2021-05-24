package com.example.nullproject2.resources;


import com.example.nullproject2.entity.User;
import com.example.nullproject2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getHospitalAttributesById/{username}")
    public User getHospital(@PathVariable String username) {
        return userRepository.getHospital(username);
    }


    @PostMapping ("/updateUser")
    public String updateUser (@RequestBody User newUser){
        userRepository.save(newUser);
        return "Added user with id: " + newUser.getId();
    }
}

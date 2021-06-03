package com.example.nullproject2;


import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.repositories.*;
import com.example.nullproject2.resources.PatientController;
import com.example.nullproject2.resources.UserController;
import com.example.nullproject2.resources.VaccineController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private VaccineController vac;

    @Autowired
    private PatientController pat;

    @Autowired
    private UserController us;

    private User user = new User();
    private VaccineRepository vaccineRepository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public DbSeeder(VaccineRepository vaccineRepository, PatientRepository patientRepository, UserRepository userRepository, RoleRepository roleRepository)
    {
        this.vaccineRepository = vaccineRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {


//        //Roles
//
//        Role role1 = new Role(Erole.ROLE_USER);
//        Role role2 = new Role(Erole.ROLE_MODERATOR);
//
//        this.roleRepository.deleteAll();
//        roleRepository.save(role1);
//        roleRepository.save(role2);


    }
}

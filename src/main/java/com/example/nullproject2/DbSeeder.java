package com.example.nullproject2;


import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.*;
import com.example.nullproject2.roles.Erole;
import com.example.nullproject2.roles.Role;
import com.github.javafaker.Faker;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DbSeeder implements CommandLineRunner {

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

        Faker faker = new Faker();

//        for (int i = 0 ; i < 4 ; i++){
//            User user1 = new User();
//            user1.setName(faker.medical().hospitalName());
//            user1.setAddress(faker.address().streetAddress());
//            user1.setPhone_number(faker.phoneNumber().phoneNumber());
//            user1.setCity(faker.address().cityName());
//            user1.setCountry(faker.address().country());
//            user1.setAvailable_doses(25);
//            user1.set
//        }
    }
}

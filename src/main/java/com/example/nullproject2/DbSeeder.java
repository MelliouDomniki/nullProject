package com.example.nullproject2;



import com.example.nullproject2.repositories.*;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;


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

    }
}

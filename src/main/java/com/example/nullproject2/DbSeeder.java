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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

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

        //VACCINES
        Date d1 = new Date( 2021, 06, 21);

        Vaccine v1 = new Vaccine(Brand.ASTRAZENECA, VaccineStatus.AVAILABLE, d1);
        Vaccine v2 = new Vaccine( Brand.PFIZER, VaccineStatus.AVAILABLE, d1);
        Vaccine v3 = new Vaccine( Brand.PFIZER, VaccineStatus.AVAILABLE,  d1);


        this.vaccineRepository.deleteAll();

        vaccineRepository.save(v1);
        vaccineRepository.save(v2);
        vaccineRepository.save(v3);


        //HOSPITALS
        User h1 = new User( "AXEPA", "Mpotsari 23", "2310874534", "Thess", "GR", 32, "axepa@gmail.com", "axepa", "axepa@");
        User h2 = new User( "IPPOKRATIO", "Mpotsari 24", "2310654534", "Thess", "GR", 67, "ippokratio@gmail.com", "ippokratio","ippokratio@" );

        this.userRepository.deleteAll();

        userRepository.save(h1);
        userRepository.save(h2);


//        //PATIENS
//
        Patient p1 = new Patient("Marika", 98, "Marikas 7", PatientStatus.AVAILABLE, "", "42565767", Sex.FEMALE);
        Patient p2 = new Patient("Sofoula", 94, "Sofoulas 54", PatientStatus.COMPLETED, "Piretos, ponos sto xeri", "86957464", Sex.FEMALE);
        Patient p3 = new Patient("Dina", 97, "Dinas 23", PatientStatus.PENDING, "", "874756356", Sex.FEMALE);

        this.patientRepository.deleteAll();

        patientRepository.save(p1);
        patientRepository.save(p2);
        patientRepository.save(p3);



        //Roles

        Role role1 = new Role(Erole.ROLE_USER);
        Role role2 = new Role(Erole.ROLE_MODERATOR);

        this.roleRepository.deleteAll();
        roleRepository.save(role1);
        roleRepository.save(role2);

        Date date1 = new Date(2021, 07, 21);

        String appoin1 = BigchainCall.doCreate(h1.getKeys(), p1.getId(), date1, v1.getVaccine_id(), PatientStatus.PENDING );
    }
}

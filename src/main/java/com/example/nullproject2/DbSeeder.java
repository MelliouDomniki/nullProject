package com.example.nullproject2;


import ch.qos.logback.core.CoreConstants;
import com.example.nullproject2.entity.Hospital;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.HospitalRepository;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private VaccineRepository vaccineRepository;
    private HospitalRepository hospitalRepository;
    private PatientRepository patientRepository;

    public DbSeeder(VaccineRepository vaccineRepository, HospitalRepository hospitalRepository,PatientRepository patientRepository)
    {
        this.vaccineRepository = vaccineRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //VACCINES
        Date d1 = new Date( 2021, 06, 21);

        Vaccine v1 = new Vaccine("12", Brand.ASTRAZENECA, VaccineStatus.AVAILABLE, d1);
        Vaccine v2 = new Vaccine("13", Brand.PFIZER, VaccineStatus.AVAILABLE, d1);
        Vaccine v3 = new Vaccine("14", Brand.PFIZER, VaccineStatus.AVAILABLE,  d1);


        this.vaccineRepository.deleteAll();

        vaccineRepository.save(v1);
        vaccineRepository.save(v2);
        vaccineRepository.save(v3);

 //       System.out.println("****");
//        List<Vaccine> vaccines = vaccineRepository.findAll();

//        for (Vaccine i: vaccines){
//            System.out.println(i.toString());
//        }

        //HOSPITALS
        Hospital h1 = new Hospital(123, "AXEPA", "Mpotsari 23", "2310874534", "Thess", "GR", 32, "axepa@gmail.com", "axepa@" );
        Hospital h2 = new Hospital(124, "IPPOKRATIO", "Mpotsari 24", "2310654534", "Thess", "GR", 67, "ippokratio@gmail.com", "ippokratio@" );

        this.hospitalRepository.deleteAll();

        hospitalRepository.save(h1);
        hospitalRepository.save(h2);


        //PATIENS

        Patient p1 = new Patient(1,"Marika", 98, "Marikas 7", PatientStatus.AVAILABLE, "", "42565767", Sex.FEMALE);
        Patient p2 = new Patient(2,"Sofoula", 94, "Sofoulas 54", PatientStatus.COMPLETED, "Piretos, ponos sto xeri", "86957464", Sex.FEMALE);
        Patient p3 = new Patient(3,"Dina", 97, "Dinas 23", PatientStatus.PENTING, "", "874756356", Sex.FEMALE);

        this.patientRepository.deleteAll();

        patientRepository.save(p1);
        patientRepository.save(p2);
        patientRepository.save(p3);



    }
}

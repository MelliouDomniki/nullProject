package com.example.nullproject2.fakedata;

import com.example.nullproject2.entity.Hospital;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.repositories.HospitalRepository;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.repositories.VaccineRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

public class DataGenerator {

    Faker faker = new Faker();

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    PatientRepository patientRepository;


    @Bean
    public CommandLineRunner run(HospitalRepository hospitalRepository) throws Exception{
        return (String[] args) -> {
            for (int i = 0 ; i < 5 ; i++){
                Hospital hospital1 = new Hospital();
                hospital1.setName(faker.medical().hospitalName());
                hospital1.setAddress(faker.address().fullAddress());
                hospital1.setPhone_number(faker.phoneNumber().phoneNumber());
                hospital1.setCity(faker.address().city());
                hospital1.setCountry(faker.country().name());

                hospital1.setEmail(faker.internet().emailAddress());
                hospital1.setUsername(faker.name().username());
                hospital1.setPassword("123456789");

                hospitalRepository.save(hospital1);
                makePatientsForHospital(hospital1);
//                makeVaccinrsForHospital(hospital1);
            }
            hospitalRepository.findAll().forEach(hospital -> System.out.println(hospital));
        };
    }

    private void makePatientsForHospital(Hospital hospital1) {
        for (int i = 0 ; i < RandomnessProvider.getRandomNumberBetween(10,20) ; i++) {
            Patient patient1 = new Patient();
            patient1.setName(faker.name().fullName());
            patient1.setAge(RandomnessProvider.getRandomNumberBetween(14,100));
            patient1.setAddress(faker.address().fullAddress());
            patient1.setStatus(RandomnessProvider.getPatientStatus());
            patient1.setAmka(faker.number().digits(12));
            patient1.setSex(RandomnessProvider.getSex());
            patientRepository.save(patient1);
        }
    }

//    private void makeVaccinrsForHospital(Hospital hospital1){
//        for (int i = 0 ; i < RandomnessProvider.getRandomNumberBetween(20,35) ; i++) {
//            Vaccine vaccine1 = new Vaccine();
//            vaccine1.setBrand(RandomnessProvider.getBrand());
//            vaccine1.setStatus(RandomnessProvider.getVaccineStatus());
//            vaccine1.setDate(LocalDate.of(2021,));
//        }
//    }
}

package com.example.nullproject2.resources;

import com.example.nullproject2.BigchainCall;
import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class VaccinationController {

    @Autowired
    private VaccineController vac;

    @Autowired
    private PatientRepository pat;

    @Autowired
    private UserController us;

  @PostMapping("{username}/vaccination/{amka}")
  public String createVaccination(@PathVariable String username,@PathVariable String amka,@RequestBody BigChain input) throws Exception {

      User hospital = us.getHospital(username);
      Patient patient = pat.findFirstByAmka(amka);
      Vaccine vaccine = vac.getVaccineByBrandAndStatus(hospital.getUsername(),Brand.valueOf(input.getBrand()), VaccineStatus.AVAILABLE);
      BigchainCall.doCreate(hospital, patient, input.getDate(), vaccine);
      return "Vaccination created";
    }

}

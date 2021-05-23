package com.example.nullproject2.resources;

import com.example.nullproject2.BigchainCall;
import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class VaccinationController {

    @Autowired
    private VaccineController vac;




  @PostMapping("{username/vaccination")
  public String createVaccination(@RequestBody BigChain input) throws Exception {

      //vrisko poio hospital einai apo to id
      //vrisko poios asthenis einai apo to AMKA
      //vrisko available dose apo brand


      //BigchainCall.doCreate(hospital, patient, input.getDate(), vaccine);
      return "Vaccination created";
    }

}

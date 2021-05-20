package com.example.nullproject2.resources;

import com.example.nullproject2.BigchainCall;
import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class VaccinationController {

  @PostMapping("/vaccination")
  public String createVaccination(@RequestBody BigChain input) throws Exception {

      return BigchainCall.doCreate(input.getHospital(), input.getPatient(), input.getDate(), input.getVaccine());
    }

}

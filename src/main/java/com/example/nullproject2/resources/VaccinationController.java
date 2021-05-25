package com.example.nullproject2.resources;

import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.model.Output;
import com.bigchaindb.model.Outputs;
import com.bigchaindb.model.Transaction;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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
      Optional<Patient> patient = pat.findFirstById(input.getId());
      Vaccine vaccine = vac.getVaccineByBrandAndStatus(hospital.getUsername(),Brand.valueOf(input.getBrand()), VaccineStatus.AVAILABLE);
     // BigchainCall.doCreate(hospital, patient, input.getDate(), vaccine);
      return "Vaccination created";
    }

//    @GetMapping("{username}/getVaccinations")
//    public List<Object> getVaccinations(@PathVariable String username) throws IOException {
//
//        ArrayList<Object> lista = new ArrayList<>();
//        User hospital = us.getHospital(username);
//        List<Output> out = OutputsApi.getOutputs(hospital.getPublicKey()).getOutput();
//        for (Output o : out)
//        {
//            Object comp ;
//            Transaction t = TransactionsApi.getTransactionById(o.getTransactionId());
//            System.out.println(t.getAsset().getData().toString());
//
//
//        }
//        return lista;
//    }

}

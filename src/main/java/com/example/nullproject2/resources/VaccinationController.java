package com.example.nullproject2.resources;

import com.bigchaindb.api.AssetsApi;
import com.bigchaindb.api.OutputsApi;
import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.constants.BigchainDbApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.*;
import com.bigchaindb.util.JsonUtils;
import com.bigchaindb.util.NetworkUtils;
import com.example.nullproject2.BigchainCall;
import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccinationStatus;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.repositories.UserRepository;
import com.example.nullproject2.repositories.VaccineRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/{username}/vaccination")
public class VaccinationController {

    @Autowired
    private VaccineController vac;

    @Autowired
    private VaccineRepository vacrepo;

    @Autowired
    private PatientRepository pat;

    @Autowired
    private UserRepository usrepo;

    @Autowired
    private UserController us;

    @Autowired
    private MongoTemplate mongoTemplate;

    private BigchainCall bigchain = new BigchainCall();


  @PostMapping("/")
  public String createVaccination(@PathVariable String username,@RequestBody BigChain input) throws Exception {

      User hospital = us.getHospital(username);
      Patient patient = pat.findFirstById(input.getId());
      //Vaccine vaccine = vac.getVaccineByBrandAndStatus(hospital.getUsername(),Brand.valueOf(input.getBrand()), VaccineStatus.AVAILABLE);

      if (patient.getStatus()=="2/2")
          return "Patient is fully vaccinated";
      else
      {
          bigchain.doCreate(hospital, patient, input.getDate(), input.getBrand());
          patient.setAppoint(1);
          patient.setBrand(Brand.valueOf(input.getBrand()));
          patient.setHospitalName(username);
          pat.save(patient);
          return "Vaccination created";
      }

    }

    @PostMapping("/update/{transid}")
    public String updateVaccination(@PathVariable String username,@PathVariable String transid, @RequestBody BigChain input) throws Exception {


        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("app_id", "")
                .addToken("app_key", "").setup();
        User hospital = us.getHospital(username);
        Patient patient = pat.findFirstByAmka(input.getAMKA());
        Vaccine vaccine = vac.getVaccineByBrandAndStatus(hospital.getUsername(),Brand.valueOf(input.getBrand()), VaccineStatus.AVAILABLE);
        String assetid = "";
        assetid = TransactionsApi.getTransactionById(transid).getAsset().getId();
        if (assetid==null)  assetid=transid;
        bigchain.doUpdate(transid,assetid, input.getStatus(), hospital, patient, input.getDate(), vaccine);

           if (input.getStatus().equals("CANCELLED"))
           {
               patient.setAppoint(0);
               patient.setHospitalName(null);
               pat.save(patient);
           }
           else if (input.getStatus().equals("DONE"))
           {
               patient.setAppoint(0);
               patient.setHospitalName(null);
               if (patient.getStatus().equals("0/2"))
                   patient.setStatus("1/2");
               else if (patient.getStatus().equals("1/2"))
                   patient.setStatus("2/2");
               patient.setSymptoms(input.getSymptoms());
               pat.save(patient);
               vac.decreaseAvailable(vaccine,hospital);
           }

            return "Vaccination created";


    }

    @PostMapping("/transfer/{transid}")
    public String transferVaccination(@PathVariable String username,@PathVariable String transid, @RequestBody BigChain input) throws Exception {


        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("app_id", "")
                .addToken("app_key", "").setup();
        User hospital = us.getHospital(username);
        User next = us.getHospital(input.getNext());
        Patient patient = pat.findFirstByAmka(input.getAMKA());
        Vaccine vaccine = vac.getVaccineByBrandAndStatus(next.getUsername(),Brand.valueOf(input.getBrand()), VaccineStatus.AVAILABLE);
        String assetid = "";
        assetid = TransactionsApi.getTransactionById(transid).getAsset().getId();
        if (assetid==null)  assetid=transid;
        bigchain.doTransfer(transid,assetid, input.getStatus(), hospital, next, patient, input.getDate(), vaccine);

       patient.setHospitalName(input.getNext());
       pat.save(patient);

        return "Vaccination was transfered";

    }

    //gia pinaka vaccinations
    @GetMapping("/all")
    public ArrayList<Object[]> getVaccinations(@PathVariable String username) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("app_id", "")
                .addToken("app_key", "").setup();
        ArrayList<Object[]> lista = new ArrayList<>();
        User hospital = us.getHospital(username);
        List<Output> out = OutputsApi.getUnspentOutputs(hospital.getPublicKey()).getOutput();
        for (Output o : out)
        {
            Object[] pin = new Object[4];
            Transaction t = TransactionsApi.getTransactionById(o.getTransactionId());

            if (t.getAsset().getData()!= null)
                pin[0] = t.getAsset().getData() ;
            else
            {
                Assets assets = AssetsApi.getAssets(t.getAsset().getId());
                for (Asset a : assets.getAssets())
                    pin[0]= a.getData();
            }
            pin[1] = t.getMetaData();
            pin[2] = t.getId();
            Gson gson = new Gson();
            LinkedTreeMap<String,String> yourMap = (LinkedTreeMap)t.getMetaData();
            JsonObject jsonObject = gson.toJsonTree(yourMap).getAsJsonObject();
            myMetadata meta =  gson.fromJson(jsonObject.toString(), myMetadata.class);
            if (vacrepo.countByBrandAndStatus(Brand.valueOf(meta.getBrand()),VaccineStatus.AVAILABLE)>0)
                pin[3]= true;
            else
                pin[3]=false;

            lista.add(pin);
        }
        return lista;
    }

    //gia statistics
    @GetMapping("/allHospitals")
    public ArrayList<ArrayList<Object[]>> getAllVaccinations(@PathVariable String username) throws IOException {

        ArrayList<ArrayList<Object[]>> lista = new ArrayList<>();

        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        for (User h : usrepo.findAll())
        {
            lista.add(getVaccinations(h.getUsername()));

        }

        return lista;
    }

    @Data
    public class myMetadata {
      private String date;
      private String name;
      private String city;
       private String country;
       private String brand;
      private String status;
    }

}

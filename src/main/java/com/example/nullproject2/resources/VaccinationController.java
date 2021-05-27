package com.example.nullproject2.resources;

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
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PatientRepository pat;

    @Autowired
    private UserRepository usrepo;

    @Autowired
    private UserController us;


  @PostMapping("/")
  public String createVaccination(@PathVariable String username,@RequestBody BigChain input) throws Exception {

      User hospital = us.getHospital(username);
      Patient patient = pat.findFirstById(input.getId());
      Vaccine vaccine = vac.getVaccineByBrandAndStatus(hospital.getUsername(),Brand.valueOf(input.getBrand()), VaccineStatus.AVAILABLE);
      BigchainCall.doCreate(hospital, patient, input.getDate(), vaccine);
      return "Vaccination created";
    }

    @GetMapping("/all")
    public ArrayList<Object[]> getVaccinations(@PathVariable String username) throws IOException {

        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("app_id", "")
                .addToken("app_key", "").setup();
        ArrayList<Object[]> lista = new ArrayList<>();
        User hospital = us.getHospital(username);
        List<Output> out = OutputsApi.getUnspentOutputs(hospital.getPublicKey()).getOutput();
        for (Output o : out)
        {
            Object[] pin = new Object[2];
            Transaction t = TransactionsApi.getTransactionById(o.getTransactionId());
            if (t.getAsset().getData()!= null)
            {
                pin[0] = t.getAsset().getData() ;
                pin[1] = t.getMetaData();
                lista.add(pin);
            }

        }
        return lista;
    }

    @GetMapping("/allHospitals")
    public ArrayList<ArrayList<Object[]>> getAllVaccinations(@PathVariable String username) throws IOException {



        ArrayList<ArrayList<Object[]>> lista = new ArrayList<>();

        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("app_id", "")
                .addToken("app_key", "").setup();
        ArrayList<Object[]> l = new ArrayList<>();


        for (User h : usrepo.findAll())
        {
            List<Output> out = OutputsApi.getUnspentOutputs(h.getPublicKey()).getOutput();
            for (Output o : out)
            {
                Object[] pin = new Object[2];
                Transaction t = TransactionsApi.getTransactionById(o.getTransactionId());
                if (t.getAsset().getData()!= null)
                {
                    pin[0] = t.getAsset().getData() ;
                    pin[1] = t.getMetaData();
                    l.add(pin);
                }
              }

            lista.add(l);

        }
        return lista;
    }



}

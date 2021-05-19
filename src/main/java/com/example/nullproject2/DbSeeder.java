package com.example.nullproject2;

import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.model.Transactions;
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

import java.security.KeyPair;
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

        new BigchainCall();

        //String appoin1 = BigchainCall.doCreate(h1, p1, date1, v1.getVaccine_id());
        KeyPair keys = BigchainCall.getKeys();
//        System.out.println(keys.getPublic());
        KeyPair keys2 = BigchainCall.getKeys();
//        System.out.println(keys2.getPublic());
//        KeyPair keys3 = BigchainCall.getKeys();
//        System.out.println(keys3.getPublic());
        String id = BigchainCall.doCreate(keys);
//        MetaData trmetadata = new MetaData();
//        trmetadata.setMetaData("date", date1.toString());
        BigchainCall.doTransfer(id,keys, keys2);
        //BigchainCall.doTransfer(id, date1, h2, v2.getVaccine_id(), PatientStatus.COMPLETED);
        //BigchainCall.doTransfer(id, date1, h2, v2.getVaccine_id(), PatientStatus.COMPLETED, keys2);
//        Transactions t = TransactionsApi.getTransactionsByAssetId(id, Operations.CREATE);
//        for (Transaction tr: t.getTransactions())
//            System.out.println(t.getTransactions().toString());






    }
}

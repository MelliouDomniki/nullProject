package com.example.nullproject2;

import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.cryptoconditions.types.Ed25519Sha256Condition;
import com.bigchaindb.model.*;
import com.bigchaindb.util.Base58;
import com.bigchaindb.util.KeyPairUtils;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.PatientStatus;
import com.example.nullproject2.enumerations.Sex;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.*;
import com.example.nullproject2.resources.UserController;
import com.example.nullproject2.resources.VaccineController;
import com.example.nullproject2.roles.Erole;
import com.example.nullproject2.roles.Role;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.apache.http.util.EncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Date;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private VaccineController vac;

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
//        Date d1 = new Date( 2021, 06, 21);

//        Vaccine v1 = new Vaccine(Brand.ASTRAZENECA, VaccineStatus.AVAILABLE, d1);
//        Vaccine v2 = new Vaccine( Brand.PFIZER, VaccineStatus.AVAILABLE, d1);
//        Vaccine v3 = new Vaccine( Brand.PFIZER, VaccineStatus.AVAILABLE,  d1);
//
//
//        this.vaccineRepository.deleteAll();
//
//        vaccineRepository.save(v1);
//        vaccineRepository.save(v2);
//        vaccineRepository.save(v3);


//        //HOSPITALS
//        User h1 = new User( "AXEPA", "Mpotsari 23", "2310874534", "Thess", "GR", 32, "axepa@gmail.com", "axepa", "axepa@");
//        User h2 = new User( "IPPOKRATIO", "Mpotsari 24", "2310654534", "Thess", "GR", 67, "ippokratio@gmail.com", "ippokratio","ippokratio@" );
//
//       // this.userRepository.deleteAll();
//        userRepository.save(h1);
//        userRepository.save(h2);


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


        //Date date1 = new Date(2021, 07, 21);

        //String appoin1 = BigchainCall.doCreate(h1, p1, date1, v1);

//        KeyPair keys2 = BigchainCall.getKeys();
//        System.out.println(keys2.getPublic());
//        KeyPair keys3 = BigchainCall.getKeys();
//        System.out.println(keys3.getPublic());
       // String id = BigchainCall.doCreate(h1.getKeys());
//        MetaData trmetadata = new MetaData();
//        trmetadata.setMetaData("date", date1.toString());
      //  String id2 = BigchainCall.doTransfer(id,h1.getKeys(), h1.getKeys());
       // System.out.println(userRepository.getHospital("axepa@gmail.com").get().getPublicKey());
       // BigchainCall.doTransfer2( "c123429183d70bee8974c7c7914bc92dfb656c8e384f5391632fca5712b4159e",userRepository.getHospital("axepa@gmail.com").get().getKeyPairs(), userRepository.getHospital("axepa@gmail.com").get().getKeyPairs());
        //BigchainCall.doTransfer(id, date1, h2, v2.getVaccine_id(), PatientStatus.COMPLETED);
        //BigchainCall.doTransfer(id, date1, h2, v2.getVaccine_id(), PatientStatus.COMPLETED, keys2);
//        Transactions t = TransactionsApi.getTransactionsByAssetId(id, Operations.CREATE);
//        for (Transaction tr: t.getTransactions())
//            System.out.println(t.getTransactions().toString());






    }
}

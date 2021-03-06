package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.payload.MessageResponse;
import com.example.nullproject2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("{username}/patient/")
public class PatientController {

    @Autowired
    private PatientRepository patrepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("addPatient")
    public ResponseEntity<?> savePatient(@RequestBody Patient pat) {
        if (patrepo.existsByAmka(pat.getAmka())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: this amka is already used!"));
        }
        Patient p = new Patient(pat.getName(),pat.getAge(),pat.getAddress(),pat.getAmka(),pat.getSex());
        patrepo.save(p);

        return  ResponseEntity.ok(new MessageResponse("Patient register successfully with id: "+p.getId()));
    }

    @GetMapping("all")
    public List<Patient> getGlobalPatients() {
        return patrepo.findAll();
    }

    @GetMapping("unvaccinated")
    public List<Patient> getPatients(@PathVariable String username) {

        List<Patient> lista = new ArrayList<>();
        for (Patient p : patrepo.findPatientsByHospitalNameOrHospitalNameIsNull(username))
        {
            if (p.getAppoint()==0 && !p.getStatus().equals("2/2"))
                lista.add(p);
        }
        return lista ;
    }

    @GetMapping("findId/{id}")
    public Optional<Patient> getPatient(@PathVariable String id) {
        return patrepo.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public String deletePatient(@PathVariable String id) {
        patrepo.deleteById(id);
        return "patient deleted with id : " + id;
    }

    @PostMapping("update")
    public String updatePatient(@PathVariable String username,@RequestBody Patient newPatient) {
        //Patient patient = mongoTemplate.findOne(Query.query(Criteria.where("hospital").is(username)), Patient.class);
        patrepo.save(newPatient);
        Update update = new Update();


        return "Updated patient with id: " + newPatient.getId();
    }

    @GetMapping("findByAMKA/{AMKA}")
    public Patient patientsByAMKA (@PathVariable String AMKA)
    {
        return patrepo.findFirstByAmka(AMKA);
    }

    @GetMapping("findByName/{name}")
    public Patient patientsByName (@PathVariable String name)
    {
        return patrepo.findByName(name);
    }

    //AND QUERY
    @GetMapping("findByNameAndAge/{name, age}")
    public List<Patient> patientsByNameAndAge (@PathVariable String name, int age)
    {
        return patrepo.findByNameAndAge(name, age);
    }

    //OR QUERY
    @GetMapping("findByNameOrAge/{name, age}")
    public List<Patient> patientsByNameOrAge (@PathVariable String name, int age)
    {
        return patrepo.findByNameOrAge(name, age);
    }

    //LIKE QUERY
    @GetMapping("like/{name}")
    public List<Patient> getAllPatientsWithNameLike (@PathVariable String name){
        return patrepo.findByNameIsLike(name);
    }

    //STARTS WITH QUERY
    @GetMapping("nameStarting/{name}")
    public List<Patient> getAllPatientsWithNameStarting (@PathVariable String name){
        return patrepo.findByNameStartsWith(name);
    }
}

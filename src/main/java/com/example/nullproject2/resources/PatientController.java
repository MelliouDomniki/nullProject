package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patrepo;

    @PostMapping("/addPatient")
    public String savePatient(@RequestBody Patient patient) {
        patrepo.save(patient);
        return "Added pattient with id: " + patient.getPatient_id();
    }

    @GetMapping("/findAllPatients")
    public List<Patient> getPatients() {
        return patrepo.findAll();
    }

    @GetMapping("/findAllPatients/{id}")
    public Optional<Patient> getPatient(@PathVariable String id) {
        return patrepo.findById(id);
    }

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable String id) {
        patrepo.deleteById(id);
        return "patient deleted with id : " + id;
    }
}

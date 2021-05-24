package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.payload.MessageResponse;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.services.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PatientController {

    @Autowired
    private PatientRepository patrepo;
    @Autowired
    private PatientServiceImpl patservice;

    @PostMapping("/addPatient")
    public ResponseEntity<?> savePatient(@RequestBody Patient patient) {
        if (patrepo.existsByAmka(patient.getAmka())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: this amka is already is use!"));
        }
        patrepo.save(patient);
        return  ResponseEntity.ok(new MessageResponse("Patient register successfully with id: "+patient.getId()));
    }

    @GetMapping("/findAllPatients")
    public List<Patient> getPatients() {
        return patrepo.findAll();
    }

    @GetMapping("/findPatientById/{id}")
    public Optional<Patient> getPatient(@PathVariable String id) {
        return patrepo.findById(id);
    }

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable String id) {
        patrepo.deleteById(id);
        return "patient deleted with id : " + id;
    }

    @PostMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient newPatient) {
        patrepo.save(newPatient);
        return "Updated patient with id: " + newPatient.getId();
    }

    @GetMapping("/patientsByAMKA/{AMKA}")
    public Patient patientsByAMKA (@PathVariable String AMKA)
    {
        return patrepo.findByAmka(AMKA);
    }

    @GetMapping("/patientsByName/{name}")
    public List<Patient> patientsByName (@PathVariable String name)
    {
        return patrepo.findByName(name);
    }

    //AND QUERY
    @GetMapping("/patientsByNameAndAge/{name, age}")
    public List<Patient> patientsByNameAndAge (@PathVariable String name, int age)
    {
        return patrepo.findByNameAndAge(name, age);
    }

    //OR QUERY
    @GetMapping("/patientsByNameOrAge/{name, age}")
    public List<Patient> patientsByNameOrAge (@PathVariable String name, int age)
    {
        return patrepo.findByNameOrAge(name, age);
    }

    //PAGINATION
    @GetMapping("/patientsWithPagination")
    public List<Patient> getAllPatientsWithPagination(@RequestParam int pages, @RequestParam int size) {
        return patservice.getAllPatientsWithPagination(pages, size);
    }

    //SORTING
    @GetMapping("/patientsWithSortingByName")
    public List<Patient> getAllPatientsWithSortingByName() {
        return patservice.getAllPatientsWithSortingByName();
    }

    //LIKE QUERY
    @GetMapping("/patientsWithNameLike/{name}")
    public List<Patient> getAllPatientsWithNameLike (@PathVariable String name){
        return patrepo.findByNameIsLike(name);
    }

    //STARTS WITH QUERY
    @GetMapping("/patientsWithNameStarting/{name}")
    public List<Patient> getAllPatientsWithNameStarting (@PathVariable String name){
        return patrepo.findByNameStartsWith(name);
    }
}

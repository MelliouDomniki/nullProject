package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Hospital;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.models.PatientModel;
import com.example.nullproject2.repositories.PatientRepository;
import com.example.nullproject2.services.PatientService;
import com.example.nullproject2.services.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patrepo;
    @Autowired
    private PatientServiceImpl patservice;

    @PostMapping("/addPatient")
    public String savePatient(@RequestBody Patient patient) {
        patrepo.save(patient);
        return "Added pattient with id: " + patient.getPatient_id();
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

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient newPatient) {
        patrepo.save(newPatient);
        return "Updated pattient with id: " + newPatient.getPatient_id();
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

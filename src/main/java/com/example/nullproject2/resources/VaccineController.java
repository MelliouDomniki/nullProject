package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VaccineController {

    @Autowired
    private VaccineRepository vacrepo;

    @PostMapping("/addVaccine")
    public String saveVaccine (@RequestBody Vaccine vaccine){
        vacrepo.save(vaccine);
        return "Added vaccine with id: " + vaccine.getVaccine_id();
    }

    @GetMapping("/findAllVaccines")
    public List<Vaccine> getVaccines() {
        return vacrepo.findAll();
    }

    @GetMapping("/findAllVaccines/{id}")
    public Optional<Vaccine> getVaccine(@PathVariable String id) {
        return vacrepo.findById(id);
    }

    @DeleteMapping("/deleteVaccine/{id}")
    public String deleteVaccine(@PathVariable String id) {
        vacrepo.deleteById(id);
        return "vaccine deleted with id : " + id;
    }
}

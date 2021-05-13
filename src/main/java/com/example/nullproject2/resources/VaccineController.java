package com.example.nullproject2.resources;

import ch.qos.logback.core.status.Status;
import com.example.nullproject2.entity.Vaccine;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.VaccineRepository;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/findVaccineById/{id}")
    public Optional<Vaccine> getVaccine(@PathVariable String id) {
        return vacrepo.findById(id);
    }

    @DeleteMapping("/deleteVaccine/{id}")
    public String deleteVaccine(@PathVariable String id) {
        vacrepo.deleteById(id);
        return "vaccine deleted with id : " + id;
    }

    @PostMapping ("/updateVaccine")
    public String updateVaccine (@RequestBody Vaccine newVaccine){
        vacrepo.save(newVaccine);
        return "Added vaccine with id: " + newVaccine.getVaccine_id();
    }

    @GetMapping("/findAllVaccinesByBrand/{brand}")
    public List<Vaccine> getVaccinesByBrand(@PathVariable Brand brand) {
        return vacrepo.findByBrand(brand);
    }

    @GetMapping("/findAllVaccinesByStatus/{vaccineStatus}")
    public List<Vaccine> getVaccinesByStatus(@PathVariable VaccineStatus vaccineStatus) {
        return vacrepo.findByStatus(vaccineStatus);
    }

    @PostMapping("addVaccines/{number}")
    public String addVaccines (@PathVariable int number,@RequestBody Vaccine vaccine){
        for (int i = 0 ; i < number ; i++){
            vacrepo.save(vaccine);
        }
        return "all good";
    }
}

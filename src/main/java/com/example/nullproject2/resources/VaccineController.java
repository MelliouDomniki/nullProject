package com.example.nullproject2.resources;

import ch.qos.logback.core.status.Status;
import com.example.nullproject2.entity.Vaccine;

import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.VaccineRepository;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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
        LocalDate start =LocalDate.of(2022, 6, 8);

        //random date
        long startdate = start.toEpochDay();
        //System.out.println(startdate);
        LocalDate end = LocalDate.of(2023, 6, 8);
        long enddate = end.toEpochDay();
        //System.out.println(enddate);
        VaccineStatus s = VaccineStatus.AVAILABLE;
        Brand b;

        for (int i = 0 ; i < number ; i++){

            long randomEpochDay = ThreadLocalRandom.current().nextLong(startdate, enddate);
            Date d = new Date(randomEpochDay);
            if (i==0 || i==3 || i==8)
                b = Brand.ASTRAZENECA;
            else if (i==1 || i==7)
                b = Brand.JOHNSON;
            else if (i==2 || i==5 || i==9)
                b = Brand.MODERNA;
            else if (i==4)
                b = Brand.PFIZER;
            else
                b = Brand.NOVAVAX;

            Vaccine v = new Vaccine(b,s,d);
            vacrepo.save(v);
        }
        return "all good";
    }
}

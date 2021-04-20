package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Hospital;
import com.example.nullproject2.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HospitalController {

    @Autowired
    private HospitalRepository hosrepo;

    @PostMapping("/addHospital")
    public String saveHospital (@RequestBody Hospital hospital){
        hosrepo.save(hospital);
        return "Added hospital with id: " + hospital.getHospital_id();
    }

    @GetMapping("/findAllHospitals")
    public List<Hospital> getHospitals() {
        return hosrepo.findAll();
    }

    @GetMapping("/findHospitalById/{id}")
    public Optional<Hospital> getHospital(@PathVariable String id) {
        return hosrepo.findById(id);
    }

    @PutMapping("/updateHospital")
    public String updateHospital (@RequestBody Hospital newHospital){
        hosrepo.save(newHospital);
        return "Updated hospital with id: " + newHospital.getHospital_id();
    }

    @GetMapping("/findHospitalsByName/{name}")
    public List<Hospital> hospitalsByName (@PathVariable String name)
    {
        return hosrepo.findByName(name);
    }

}

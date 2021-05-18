package com.example.nullproject2.resources;

import com.example.nullproject2.entity.BigChain;
import com.example.nullproject2.repositories.BigChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BigChainController {

    @Autowired
    private BigChainRepository bigChainRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/addVaccination")
    public String saveVaccination (@RequestBody BigChain bigChain){
        bigChainRepository.save(bigChain);
        return "Vaccination added";
    }

    @GetMapping("/findAllVaccinations")
    public List<BigChain> getVaccinations(){
        return bigChainRepository.findAll();
    }

    @GetMapping("/findVaccinationsByAge/{age}")
    public List<BigChain> getVaccinationsByAge(@PathVariable int age){
        Query query = new Query();
        query.addCriteria(Criteria.where("age").is(age));
        return bigChainRepository.findByAge(age);
    }

    @GetMapping("/findVaccinationsByHospital/{hospital}")
    public List<BigChain> getVaccinationsByHospital(@PathVariable String hospital) {
        Query query = new Query();
        query.addCriteria(Criteria.where("hospital").is(hospital));
        return bigChainRepository.findByHospital(hospital);
    }

    @GetMapping("/findVaccinationsByCity/{city}")
    public List<BigChain> getVaccinationsByCity(@PathVariable String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        return bigChainRepository.findByCity(city);
    }

    @GetMapping("/findVaccinationsByCountry/{country}")
    public List<BigChain> getVaccinationsByCountry(@PathVariable String country) {
        Query query = new Query();
        query.addCriteria(Criteria.where("country").is(country));
        return bigChainRepository.findByCountry(country);
    }
}

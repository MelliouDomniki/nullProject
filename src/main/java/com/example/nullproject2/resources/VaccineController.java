package com.example.nullproject2.resources;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.fakedata.RandomnessProvider;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("{username}/addVaccine")
    public String saveVaccine (@PathVariable String username,@RequestBody Vaccine vaccine){
        Update update = new Update();
        update.addToSet("vaccines", vaccine);
        Criteria criteria = Criteria.where("username").is(username);
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");
        vacrepo.save(vaccine);
        return "Added vaccine with id: " + vaccine.getId();
    }

    @GetMapping("{username}/findAllVaccines")
    public List<User> getVaccines(@PathVariable String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.fields().include("vaccines");
        return mongoTemplate.find(query, User.class, "users");
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
        return "Added vaccine with id: " + newVaccine.getId();
    }

    @GetMapping("{username}/findAllVaccinesByBrand/{brand}")
    public List<User> getVaccinesByBrand(@PathVariable String username, @PathVariable Brand brand) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username).andOperator(Criteria.where("brand").is(brand)));
        return mongoTemplate.find(query, User.class, "users");
    }

    //to allaxa
    @GetMapping("{username}/findAllVaccinesByStatus/{vaccineStatus}")
    public List<User> getVaccinesByStatus(@PathVariable String username, @PathVariable VaccineStatus vaccineStatus) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username).andOperator(Criteria.where("status").is(vaccineStatus)));
        return mongoTemplate.find(query, User.class, "users");
    }

    @PostMapping("{username}/addVaccines/{number}")
    public String addVaccines (@PathVariable String username,@PathVariable int number){

        Update update = new Update();
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
            b = RandomnessProvider.getBrand();

            Vaccine v = new Vaccine(b,s,d,username);
            vacrepo.save(v);
            update.addToSet("vaccines", v);
            Criteria criteria = Criteria.where("username").is(username);
            mongoTemplate.updateFirst(Query.query(criteria), update, "users");

        }
        return "all good";
    }
}
